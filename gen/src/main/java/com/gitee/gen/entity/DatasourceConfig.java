package com.gitee.gen.entity;

/**
 * 数据源配置表
 */
public class DatasourceConfig {

    private Integer id;
    /**
     * 数据库类型，1：MySql, 2:Oracle, 3:sqlserver
     */
    private Integer dbType;
    /**
     * 数据库驱动
     */
    private String driverClass;
    /**
     * 数据库名称
     */
    private String dbName;
    /**
     * 数据库host
     */
    private String host;
    /**
     * 数据库端口
     */
    private Integer port;
    /**
     * 数据库用户名
     */
    private String username;
    /**
     * 数据库密码
     */
    private String password;
    /**
     * 是否已删除，1：已删除，0：未删除
     */
    private Integer isDeleted;
    /**
     * 包名
     */
    private String packageName;
    /**
     * 删除的表前缀
     */
    private String delTablePrefix;
    /**
     * 删除的字段前缀
     */
    private String delFieldPrefix;
    /**
     * 代码生成器模板组id
     */
    private Integer groupId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDbType() {
        return dbType;
    }

    public void setDbType(Integer dbType) {
        this.dbType = dbType;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
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

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"dbType\":")
                .append(dbType);
        sb.append(",\"driverClass\":\"")
                .append(driverClass).append('\"');
        sb.append(",\"dbName\":\"")
                .append(dbName).append('\"');
        sb.append(",\"host\":\"")
                .append(host).append('\"');
        sb.append(",\"port\":")
                .append(port);
        sb.append(",\"username\":\"")
                .append(username).append('\"');
        sb.append(",\"password\":\"")
                .append(password).append('\"');
        sb.append(",\"isDeleted\":")
                .append(isDeleted);
        sb.append(",\"packageName\":\"")
                .append(packageName).append('\"');
        sb.append(",\"delTablePrefix\":\"")
                .append(delTablePrefix).append('\"');
        sb.append(",\"delFieldPrefix\":\"")
                .append(delFieldPrefix).append('\"');
        sb.append(",\"groupId\":")
                .append(groupId);
        sb.append('}');
        return sb.toString();
    }
}
