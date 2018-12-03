package com.dingshen.rongaixiang.service;

import com.dingshen.rongaixiang.domain.Contact;
import com.github.pagehelper.Page;

import java.util.Map;

public interface ContactService {
     int deleteByPrimaryKey(Integer id);

     int insert(Contact record);

    Contact selectByPrimaryKey(Integer id);

    Page<Map> findAllContact(Integer page, Integer limit, String type, String keyword);

}
