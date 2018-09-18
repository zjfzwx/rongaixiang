package com.dingshen.rongaixiang.service.impl;

import com.dingshen.rongaixiang.domain.Job;
import com.dingshen.rongaixiang.mapper.JobMapper;
import com.dingshen.rongaixiang.service.JobService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class JobServiceImpl implements JobService {
    @Autowired
    JobMapper jobMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return jobMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Job record) {
        return jobMapper.insert(record);
    }

    @Override
    public Job selectByPrimaryKey(Integer id) {
        return jobMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(Job record) {
        return jobMapper.updateByPrimaryKey(record);
    }

    @Override
    public Page<Map> findAllJob(Integer page, Integer limit, String type, String keyword) {
        Map params = new HashMap();

        if(type != null && !type.equals("-1")){
            params.put("type" , type);
        }
        if(keyword != null && !keyword.trim().equals("")){
            params.put("keyword" , "%" + keyword + "%");
        }
        //启用分页
        PageHelper.startPage (page,limit );
        Page<Map> list=(Page)jobMapper.findAllJob(params);
        return list ;
    }

    @Override
    public List<Map> getJobList() {
        return jobMapper.getJobList();
    }
}
