package com.cuit.service;

import com.cuit.entity.Appointment;
import com.cuit.entity.HeadMaster;
import com.cuit.entity.School;

import java.util.List;

public interface SchoolAndHeadMasterService {

    int updatePassword(String newPassword);//修改WEB-INT/admin文件实现

    int insertHeadMaster(String headMasterName);

    int insertSchool(String schoolName);

    int updateHeadMasterByCode(HeadMaster headMaster);

    int updateSchoolByCode(School school);

    int deleteHeadMasterByCode(int code);

    int deleteSchoolByCode(int code);

    int loginCheck(String username, String password);

    int selectAppointmentNumberByHCode(String date, int hCode);
}
