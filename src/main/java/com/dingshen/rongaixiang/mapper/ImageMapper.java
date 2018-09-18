package com.dingshen.rongaixiang.mapper;

import com.dingshen.rongaixiang.domain.Image;

import java.util.List;
import java.util.Map;

public interface ImageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Image record);

    int insertSelective(Image record);

    Image selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Image record);

    int updateByPrimaryKey(Image record);
    List<Map> findAllImage();

    List<Integer> findAllImageIds(String productType);
    Integer findIdByName(String imageName);
}