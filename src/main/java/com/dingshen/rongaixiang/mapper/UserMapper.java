package com.dingshen.rongaixiang.mapper;

import com.dingshen.rongaixiang.domain.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> findAllUser(Map params);

    List<Map> getAllUser();
    User checkUserName(User user);
}