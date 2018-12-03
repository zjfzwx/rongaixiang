package com.dingshen.rongaixiang.service;

import com.dingshen.rongaixiang.domain.Image;

import java.util.List;
import java.util.Map;

public interface ImageService {
     int deleteByPrimaryKey(Integer id);

     int insert(Image record);

    Image selectByPrimaryKey(Integer id);

     int updateByPrimaryKey(Image record);

    List<Integer> findAllImageIds(String productType);

    List<Map> findAllImage(Integer currentPage,Integer limit);

    Integer findIdByName(String imageName);

}
