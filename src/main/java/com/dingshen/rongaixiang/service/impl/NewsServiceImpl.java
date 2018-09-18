package com.dingshen.rongaixiang.service.impl;

import com.dingshen.rongaixiang.domain.News;
import com.dingshen.rongaixiang.mapper.NewsMapper;
import com.dingshen.rongaixiang.service.NewsService;
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
public class NewsServiceImpl implements NewsService {
    @Autowired
    NewsMapper newsMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return newsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(News record) {
        return newsMapper.insert(record);
    }

    @Override
    public News selectByPrimaryKey(Integer id) {
        return newsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(News record) {
        return newsMapper.updateByPrimaryKey(record);
    }
    @Override
    public Page<Map> findAllNews(Integer page, Integer rows, String type, String keyword) {
        Map params = new HashMap();

        if(type != null && !type.equals("-1")){
            params.put("type" , type);
        }
        if(keyword != null && !keyword.trim().equals("")){
            params.put("keyword" , "%" + keyword + "%");
        }
        //启用分页
        PageHelper.startPage (page,rows );
        Page<Map> list=(Page)newsMapper.findAllNews (params);
        return list ;
    }

    @Override
    public List<Map> getAllNews() {
        return newsMapper.getAllNews();
    }

}
