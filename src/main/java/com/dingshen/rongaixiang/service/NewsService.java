package com.dingshen.rongaixiang.service;

import com.dingshen.rongaixiang.domain.News;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface NewsService {

     Page<Map> findAllNews(Integer page, Integer rows, String type, String keyword);

     List<Map> getAllNews();

     int deleteByPrimaryKey(Integer id);

     int insert(News record);

     News selectByPrimaryKey(Integer id);

     int updateByPrimaryKey(News record);
}
