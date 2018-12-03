package com.dingshen.rongaixiang.service.impl;

import com.dingshen.rongaixiang.domain.Contact;
import com.dingshen.rongaixiang.domain.Job;
import com.dingshen.rongaixiang.mapper.ContactMapper;
import com.dingshen.rongaixiang.mapper.JobMapper;
import com.dingshen.rongaixiang.service.ContactService;
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
public class ContactServiceImpl implements ContactService {
    @Autowired
    ContactMapper contactMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return contactMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Contact record) {
        return contactMapper.insert(record);
    }

    @Override
    public Contact selectByPrimaryKey(Integer id) {
        return contactMapper.selectByPrimaryKey(id);
    }


    @Override
    public Page<Map> findAllContact(Integer page, Integer limit, String type, String keyword) {
        Map params = new HashMap();

        if(type != null && !type.equals("-1")){
            params.put("type" , type);
        }
        if(keyword != null && !keyword.trim().equals("")){
            params.put("keyword" , "%" + keyword + "%");
        }
        //启用分页
        PageHelper.startPage (page,limit );
        Page<Map> list=(Page)contactMapper.findAllContact(params);
        return list ;
    }


}
