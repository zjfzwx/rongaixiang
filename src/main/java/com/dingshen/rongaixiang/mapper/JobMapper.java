package com.dingshen.rongaixiang.mapper;

import com.dingshen.rongaixiang.domain.Job;

import java.util.List;
import java.util.Map;

public interface JobMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Job record);

    int insertSelective(Job record);

    Job selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Job record);

    int updateByPrimaryKey(Job record);

    List<Map> findAllJob(Map params);

    List<Map> getJobList();
}