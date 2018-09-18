package com.dingshen.rongaixiang.service;

import com.dingshen.rongaixiang.domain.Job;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface JobService {
     int deleteByPrimaryKey(Integer id);

     int insert(Job record);

    Job selectByPrimaryKey(Integer id);

     int updateByPrimaryKey(Job record);

    Page<Map> findAllJob(Integer page, Integer limit, String type, String keyword);

    List<Map> getJobList();
}
