package com.cuit.dao;

import com.cuit.entity.Appointment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AppointmentMapper {
    int deleteByPrimaryKey(String code);

    int insert(Appointment record);

    int insertSelective(Appointment record);

    Appointment selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(Appointment record);

    int updateByPrimaryKey(Appointment record);

    List<Appointment> selectByChoice(@Param("choiceMonth") String choiceMonth,
                                         @Param("hCode") int hCode,
                                         @Param("sCode") int sCode);

    List<Appointment> selectByPhone(String phone);

    int selectAppointmentNumberByHCode(@Param("date") String date,
                                       @Param("hCode") int hCode);
}