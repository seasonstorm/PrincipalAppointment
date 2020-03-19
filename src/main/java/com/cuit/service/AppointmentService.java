package com.cuit.service;

import com.cuit.entity.Appointment;
import com.cuit.entity.HeadMaster;
import com.cuit.entity.School;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AppointmentService {

    List<HeadMaster> selectHeadMasters() ;

    List<School> selectSchools();

    List<Appointment> selectChoiceDateAppointmentInfo(String choiceMonth, int hCode, int sCode);

    int insertAppointmentInfo(Appointment appointment);

    List<Appointment> selectByPhone(String phone);

    String selectHeadMasterNameByCode(int code);

    String selectSchoolNameByCode(int code);
}
