package com.dingshen.rongaixiang.mapper;

import com.dingshen.rongaixiang.domain.Product;

import java.util.List;
import java.util.Map;

public interface ProductMapper {
    String findProductName(String imageId);

    List<String> findAllProductType();

    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    List<Map> findAllProduct(Map params);

    List<Map> getProdcutList();
}