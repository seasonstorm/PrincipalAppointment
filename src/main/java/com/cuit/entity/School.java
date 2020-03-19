package com.cuit.entity;

/**
 * 对应数据库表的实体类
 */
public class School {
    private Integer schoolCode;

    private String schoolName;

    public Integer getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(Integer schoolCode) {
        this.schoolCode = schoolCode;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName == null ? null : schoolName.trim();
    }
}