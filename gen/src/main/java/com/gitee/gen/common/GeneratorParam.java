package com.gitee.gen.common;

import java.util.List;

/**
 * Description:
 * </p>
 *
 * @Author: xwc1125
 * @Date: 2021-01-05 11:53:17
 * @Copyright Copyright@2021
 */
public class GeneratorParam {

    /**
     * datasource_config主键
     */
    private int datasourceConfigId;

    /**
     * 表名
     */
    private List<String> tableNames;

    /**
     * template_config主键
     */
    private List<Integer> templateConfigIdList;

    private String packageName;

    /**
     * 删除table前缀
     */
    private String delTablePrefix;
    /**
     * 删除field前缀
     */
    private String delFieldPrefix;

    private String charset = "UTF-8" ;

    public int getDatasourceConfigId() {
        return datasourceConfigId;
    }

    public void setDatasourceConfigId(int datasourceConfigId) {
        this.datasourceConfigId = datasourceConfigId;
    }

    public List<String> getTableNames() {
        return tableNames;
    }

    public void setTableNames(List<String> tableNames) {
        this.tableNames = tableNames;
    }

    public List<Integer> getTemplateConfigIdList() {
        return templateConfigIdList;
    }

    public void setTemplateConfigIdList(List<Integer> templateConfigIdList) {
        this.templateConfigIdList = templateConfigIdList;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getDelTablePrefix() {
        return delTablePrefix;
    }

    public void setDelTablePrefix(String delTablePrefix) {
        this.delTablePrefix = delTablePrefix;
    }

    public String getDelFieldPrefix() {
        return delFieldPrefix;
    }

    public void setDelFieldPrefix(String delFieldPrefix) {
        this.delFieldPrefix = delFieldPrefix;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"datasourceConfigId\":")
                .append(datasourceConfigId);
        sb.append(",\"tableNames\":")
                .append(tableNames);
        sb.append(",\"templateConfigIdList\":")
                .append(templateConfigIdList);
        sb.append(",\"packageName\":\"")
                .append(packageName).append('\"');
        sb.append(",\"delTablePrefix\":\"")
                .append(delTablePrefix).append('\"');
        sb.append(",\"delFieldPrefix\":\"")
                .append(delFieldPrefix).append('\"');
        sb.append(",\"charset\":\"")
                .append(charset).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
