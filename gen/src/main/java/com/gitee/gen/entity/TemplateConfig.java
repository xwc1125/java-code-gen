package com.gitee.gen.entity;

/**
 * 模板表
 */
public class TemplateConfig {

    private Integer id;
    private Integer groupId;
    private String groupName;
    /**
     * 模板名称
     */
    private String name;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 内容
     */
    private String content;
    /**
     * 是否删除，1：已删除，0：未删除
     */
    private Integer isDeleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "TemplateConfig{" +
                "id=" + id +
                ", groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", name='" + name + '\'' +
                ", fileName='" + fileName + '\'' +
                ", content='" + content + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
