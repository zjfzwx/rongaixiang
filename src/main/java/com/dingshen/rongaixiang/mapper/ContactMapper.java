package com.dingshen.rongaixiang.mapper;

import com.dingshen.rongaixiang.domain.Contact;

import java.util.List;
import java.util.Map;

public interface ContactMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Contact record);

    Contact selectByPrimaryKey(Integer id);

    List<Map> findAllContact(Map params);

}