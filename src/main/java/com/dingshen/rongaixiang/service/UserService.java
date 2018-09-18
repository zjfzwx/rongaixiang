package com.dingshen.rongaixiang.service;

import com.dingshen.rongaixiang.domain.User;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface UserService {

     Page<Map> findAllUser(Integer page, Integer rows,String keyword);

     int deleteByPrimaryKey(Integer id);

     int insert(User record);

     User selectByPrimaryKey(Integer id);

     int updateByPrimaryKey(User record);

     List<Map> getAllUser();
}
