package com.dingshen.rongaixiang.service;

import com.dingshen.rongaixiang.domain.Product;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface ProductService {
     String findProductName(String imageId);

     List<String> findAllProductType();

     Page<Map> findAllProduct(Integer page, Integer rows, String type, String keyword);

     int deleteByPrimaryKey(Integer id);

     int insert(Product record);

     Product selectByPrimaryKey(Integer id);

     int updateByPrimaryKey(Product record);
}
