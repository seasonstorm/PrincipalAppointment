package com.cuit.service.impl;

import com.cuit.dao.AppointmentMapper;
import com.cuit.dao.HeadMasterMapper;
import com.cuit.dao.SchoolMapper;
import com.cuit.entity.Appointment;
import com.cuit.entity.HeadMaster;
import com.cuit.entity.School;
import com.cuit.service.SchoolAndHeadMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
public class SchoolAndHeadMasterServiceImpl implements SchoolAndHeadMasterService {

    @Autowired
    private SchoolMapper schoolMapper;

    @Autowired
    private HeadMasterMapper headMasterMapper;

    @Autowired
    private AppointmentMapper appointmentMapper;

    @Override
    public int loginCheck(String username, String password) {
        int result = 0;

        try {
            String path = this.getClass().getResource("/").getPath().replace("/classes", "");
            File file = new File(path + "admin");
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String data = "";
            String line = null;
            while ((line = reader.readLine()) != null) {
                data += line;
            }
            reader.close();

            int startPosition = data.indexOf("username:") + 9;
            int endPosition = data.indexOf("password:");
            String serverUsername = data.substring(startPosition, endPosition);
            String serverPassword = data.substring(endPosition + 9);

            if (serverPassword.equals(password) || serverUsername.equals(username)) result = 1;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    //修改WEB-INT/admin文件实现
    @Override
    public int updatePassword(String newPassword) {

        int status = 0;

        try {
            String path = this.getClass().getResource("/").getPath().replace("/classes", "");
            File file = new File(path + "admin");
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String data = "";
            String line = null;
            while ((line = reader.readLine()) != null) {
                data += line;
            }
            reader.close();

            int startPosition = data.lastIndexOf(":") + 1;
            String oldPassword = data.substring(startPosition);
            data = data.replace(oldPassword, newPassword);

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(data);
            writer.flush();
            writer.close();
            status = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public int insertHeadMaster(String headMasterName) {
        return headMasterMapper.insert(headMasterName);
    }

    @Override
    public int insertSchool(String schoolName) {
        return schoolMapper.insert(schoolName);
    }

    @Override
    public int updateHeadMasterByCode(HeadMaster headMaster) {
        return headMasterMapper.updateByPrimaryKey(headMaster);
    }

    @Override
    public int updateSchoolByCode(School school) {
        return schoolMapper.updateByPrimaryKey(school);
    }

    @Override
    public int deleteHeadMasterByCode(int code) {
        return headMasterMapper.deleteByPrimaryKey(code);
    }

    @Override
    public int deleteSchoolByCode(int code) {
        return schoolMapper.deleteByPrimaryKey(code);
    }

    @Override
    public int selectAppointmentNumberByHCode(String date, int hCode) {
        return appointmentMapper.selectAppointmentNumberByHCode(date, hCode);
    }
}
