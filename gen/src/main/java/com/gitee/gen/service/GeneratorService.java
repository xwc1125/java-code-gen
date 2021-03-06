package com.gitee.gen.service;

import com.gitee.gen.common.GeneratorParam;
import com.gitee.gen.entity.TemplateConfig;
import com.gitee.gen.gen.*;
import com.gitee.gen.util.FormatUtil;
import com.gitee.gen.util.VelocityUtil;
import com.gitee.gen.util.date.DateFormat;
import com.gitee.gen.util.date.DateUtils;
import org.apache.velocity.VelocityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 生成代码逻辑
 */
@Service
public class GeneratorService {

    static ExecutorService executorService = Executors.newFixedThreadPool(2);

    @Autowired
    private TemplateConfigService templateConfigService;

    @Autowired
    private GenerateHistoryService generateHistoryService;

    @Value("${gen.format-xml:false}")
    private String formatXml;

    /**
     * 生成代码内容,map的
     *
     * @param generatorParam  生成参数
     * @param generatorConfig 数据源配置
     * @return 一张表对应多个模板
     */
    public List<CodeFile> generate(GeneratorParam generatorParam, GeneratorConfig generatorConfig) {
        List<SQLContext> contextList = this.buildSQLContextList(generatorParam, generatorConfig);
        List<CodeFile> codeFileList = new ArrayList<>();

        for (SQLContext sqlContext : contextList) {
            setPackageName(sqlContext, generatorParam.getPackageName());
            setDelTablePrefix(sqlContext, generatorParam.getDelTablePrefix());
            setDelFieldPrefix(sqlContext, generatorParam.getDelFieldPrefix());
            setAuthor(sqlContext, generatorParam.getAuthor());
            for (int tcId : generatorParam.getTemplateConfigIdList()) {
                TemplateConfig template = templateConfigService.getById(tcId);
                String folder = template.getName();
                String fileName = doGenerator(sqlContext, template.getFileName());
                String content = doGenerator(sqlContext, template.getContent());
                content = this.formatCode(fileName, content);
                CodeFile codeFile = new CodeFile();
                codeFile.setFolder(folder);
                codeFile.setFileName(fileName);
                codeFile.setContent(content);
                codeFileList.add(codeFile);
            }
        }

        executorService.execute(() -> {
            generateHistoryService.saveHistory(generatorParam);
        });

        return codeFileList;
    }

    // 格式化代码
    private String formatCode(String fileName, String content) {
        if (Objects.equals("true", formatXml) && fileName.endsWith(".xml")) {
            return FormatUtil.formatXml(content);
        }
        return content;
    }

    /**
     * 返回SQL上下文列表
     *
     * @param generatorParam  参数
     * @param generatorConfig 配置
     * @return 返回SQL上下文
     */
    private List<SQLContext> buildSQLContextList(GeneratorParam generatorParam, GeneratorConfig generatorConfig) {

        List<String> tableNames = generatorParam.getTableNames();
        List<SQLContext> contextList = new ArrayList<>();
        SQLService service = SQLServiceFactory.build(generatorConfig);

        TableSelector tableSelector = service.getTableSelector(generatorConfig, generatorParam.getDelFieldPrefix());
        tableSelector.setSchTableNames(tableNames);

        List<TableDefinition> tableDefinitions = tableSelector.getTableDefinitions();

        for (TableDefinition tableDefinition : tableDefinitions) {
            SQLContext sqlContext = new SQLContext(tableDefinition);
            sqlContext.setDbName(generatorConfig.getDbName());
            contextList.add(sqlContext);
        }

        return contextList;
    }

    private void setPackageName(SQLContext sqlContext, String packageName) {
        if (StringUtils.hasText(packageName)) {
            sqlContext.setPackageName(packageName);
        }
    }

    private void setDelTablePrefix(SQLContext sqlContext, String delTablePrefix) {
        if (StringUtils.hasText(delTablePrefix)) {
            sqlContext.setDelTablePrefix(delTablePrefix);
        }
    }

    private void setDelFieldPrefix(SQLContext sqlContext, String delFieldPrefix) {
        if (StringUtils.hasText(delFieldPrefix)) {
            sqlContext.setDelFieldPrefix(delFieldPrefix);
        }
    }

    private void setAuthor(SQLContext sqlContext, String author) {
        if (StringUtils.hasText(author)) {
            sqlContext.setAuthor(author);
        } else {
            sqlContext.setAuthor("");
        }
    }

    /**
     * [core] 生成文件
     */
    private String doGenerator(SQLContext sqlContext, String template) {
        if (template == null) {
            return "" ;
        }
        VelocityContext context = new VelocityContext();

        context.put("context", sqlContext);
        context.put("table", sqlContext.getTableDefinition());
        context.put("pk", sqlContext.getTableDefinition().getPkColumn());
        context.put("columns", sqlContext.getTableDefinition().getColumnDefinitions());
        context.put("csharpColumns", sqlContext.getTableDefinition().getCsharpColumnDefinitions());
        context.put("author", sqlContext.getAuthor());
        Date nowDate = new Date();
        context.put("datetime", DateUtils.dateToFormat(nowDate, DateFormat.YYYY_MM_dd8HH0mm0ss));
        context.put("year", DateUtils.dateToFormat(nowDate, DateFormat.YYYY));

        return VelocityUtil.generate(context, template);
    }

}
