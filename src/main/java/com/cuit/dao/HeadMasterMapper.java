package com.cuit.dao;

import com.cuit.entity.HeadMaster;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeadMasterMapper {
    int deleteByPrimaryKey(Integer code);

    int insert(String name);

    int insertSelective(HeadMaster record);

    HeadMaster selectByPrimaryKey(Integer code);

    int updateByPrimaryKeySelective(HeadMaster record);

    int updateByPrimaryKey(HeadMaster record);

    List<HeadMaster> selectAll();

    String selectHeadMasterNameByCode(int code);
}