package com.cuit.dao;

import com.cuit.entity.School;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolMapper {
    int deleteByPrimaryKey(Integer schoolCode);

    int insert(String schoolName);

    School selectByPrimaryKey(Integer schoolCode);

    int updateByPrimaryKey(School record);

    List<School> selectAll();

    String selectSchoolNameByCode(int code);
}