package com.dingshen.rongaixiang.mapper;

import com.dingshen.rongaixiang.domain.News;

import java.util.List;
import java.util.Map;

public interface NewsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);

    List<Map> findAllNews(Map params);

    List<Map> getAllNews();
}