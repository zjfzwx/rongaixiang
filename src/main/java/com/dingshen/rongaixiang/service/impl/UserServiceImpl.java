package com.dingshen.rongaixiang.service.impl;

import com.dingshen.rongaixiang.domain.User;
import com.dingshen.rongaixiang.mapper.UserMapper;
import com.dingshen.rongaixiang.service.UserService;
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
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;


    @Override
    public Page<Map> findAllUser(Integer page, Integer rows, String keyword) {
        Map params = new HashMap();
        if(keyword != null && !keyword.trim().equals("")){
            params.put("keyword" , "%" + keyword + "%");
        }
        //启用分页
        PageHelper.startPage (page,rows );
        Page<Map> list=(Page)userMapper.findAllUser (params);
        return list ;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Map> getAllUser() {
        return userMapper.getAllUser();
    }


}
