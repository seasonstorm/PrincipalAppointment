package com.cuit.entity;

import java.util.Date;

/**
 * 对应数据库表的实体类
 */
public class Appointment {
    private String code;

    private Integer schoolCode;

    private Integer headmasterCode;

    private String name;

    private String phone;

    private String appointmentDate;

    private String appointmentTime;

    private Date recordTime;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Integer getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(Integer schoolCode) {
        this.schoolCode = schoolCode;
    }

    public Integer getHeadmasterCode() {
        return headmasterCode;
    }

    public void setHeadmasterCode(Integer headmasterCode) {
        this.headmasterCode = headmasterCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate == null ? null : appointmentDate.trim();
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime == null ? null : appointmentTime.trim();
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }
}