package com.cuit.service.impl;

import com.cuit.dao.AppointmentMapper;
import com.cuit.dao.HeadMasterMapper;
import com.cuit.dao.SchoolMapper;
import com.cuit.entity.Appointment;
import com.cuit.entity.HeadMaster;
import com.cuit.entity.School;
import com.cuit.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AppointmentServiceImpl implements AppointmentService {



    @Autowired
    private HeadMasterMapper headMasterMapper;

    @Autowired
    private SchoolMapper schoolMapper;

    @Autowired
    private AppointmentMapper appointmentMapper;

    @Override
    public List<HeadMaster> selectHeadMasters() {
        return headMasterMapper.selectAll();
    }

    @Override
    public List<School> selectSchools() {
        return schoolMapper.selectAll();
    }

    @Override
    public List<Appointment> selectChoiceDateAppointmentInfo(String choiceMonth, int hCode, int sCode) {
        return appointmentMapper.selectByChoice(choiceMonth, hCode, sCode);
    }

    @Override
    public int insertAppointmentInfo(Appointment appointment) {
        return appointmentMapper.insert(appointment);
    }

    @Override
    public List<Appointment> selectByPhone(String phone) {
        return appointmentMapper.selectByPhone(phone);
    }

    @Override
    public String selectHeadMasterNameByCode(int code) {
        return headMasterMapper.selectHeadMasterNameByCode(code);
    }

    @Override
    public String selectSchoolNameByCode(int code) {
        return schoolMapper.selectSchoolNameByCode(code);
    }
}
