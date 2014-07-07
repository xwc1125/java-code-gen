package org.durcframework.autocode.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.VelocityContext;
import org.durcframework.autocode.entity.CodeFile;
import org.durcframework.autocode.entity.DataSourceConfig;
import org.durcframework.autocode.entity.TemplateConfig;
import org.durcframework.autocode.generator.SQLContext;
import org.durcframework.autocode.generator.SQLService;
import org.durcframework.autocode.generator.SQLServiceFactory;
import org.durcframework.autocode.util.VelocityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneratorService {
	@Autowired
	private TemplateConfigService templateConfigService;
	/**
	 * 生成代码内容,map的
	 * @param tableNames
	 * @param tcIds
	 * @param dataSourceConfig
	 * @return 一张表对应多个模板
	 */
	public List<CodeFile> generate(List<String> tableNames,List<Integer> tcIds,DataSourceConfig dataSourceConfig){
		
		SQLService service = SQLServiceFactory.build(dataSourceConfig);
		
		List<SQLContext> contextList = service.getColumnSelector(dataSourceConfig).buildSQLContextList(tableNames);
		
		List<CodeFile> codeFileList = new ArrayList<CodeFile>();
		
		for (SQLContext sqlContext : contextList) {
			
			String tableName = sqlContext.getTableDefinition().getTableName();
			
			for (int tcId : tcIds) {
				
				TemplateConfig template = templateConfigService.get(tcId);
				String content = doGenerator(sqlContext,template.getContent());
				
				CodeFile codeFile = new CodeFile(tableName, template.getName(), content);
				
				codeFileList.add(codeFile);
			}
		}
		
		return codeFileList;
	}
	
	private String doGenerator(SQLContext sqlContext,String template){
		VelocityContext context = new VelocityContext();
		
		context.put("context", sqlContext);
		context.put("table", sqlContext.getTableDefinition());
		context.put("pkColumn", sqlContext.getTableDefinition().getPkColumn());
		context.put("columns", sqlContext.getTableDefinition().getColumnDefinitions());
		
		return VelocityUtil.generate(context, template);
	}
	
}
