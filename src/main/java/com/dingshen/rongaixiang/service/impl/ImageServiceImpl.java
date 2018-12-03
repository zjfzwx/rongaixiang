package com.dingshen.rongaixiang.service.impl;

import com.dingshen.rongaixiang.domain.Image;
import com.dingshen.rongaixiang.mapper.ImageMapper;
import com.dingshen.rongaixiang.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    ImageMapper imageMapper;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return imageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Image record) {
        return imageMapper.insert(record);
    }

    @Override
    public Image selectByPrimaryKey(Integer id) {
        return imageMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(Image record) {
        return imageMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Integer> findAllImageIds(String productType) {
        return imageMapper.findAllImageIds(productType);
    }

    @Override
    public List<Map> findAllImage(Integer currentPage,Integer limit) {
        Map map=new HashMap();
        map.put("currentPage",currentPage);
        map.put("limit",limit);
        return imageMapper.findAllImage(map);
    }

    @Override
    public Integer findIdByName(String imageName) {
        return imageMapper.findIdByName(imageName);
    }


}
