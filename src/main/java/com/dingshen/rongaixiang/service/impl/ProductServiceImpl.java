package com.dingshen.rongaixiang.service.impl;

import com.dingshen.rongaixiang.domain.Product;
import com.dingshen.rongaixiang.mapper.ProductMapper;
import com.dingshen.rongaixiang.service.ProductService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
   @Autowired
    ProductMapper productMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Product product){
        return productMapper.insert(product);
    }

    @Override
    public Product selectByPrimaryKey(Integer id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(Product record) {
        return productMapper.updateByPrimaryKey(record);
    }

    @Override
    public String findProductName(String imageId) {
        return productMapper.findProductName(imageId);
    }

    @Override
    public List<String> findAllProductType() {
        return productMapper.findAllProductType();
    }

    @Override
    public Page<Map> findAllProduct(Integer page, Integer rows, String type, String keyword) {
        Map params = new HashMap();

        if(type != null && !type.equals("-1")&&!type.equals("")){
            params.put("type" , type);
        }
        if(keyword != null && !keyword.trim().equals("")){
            params.put("keyword" , "%" + keyword + "%");
        }
        //启用分页
        PageHelper.startPage (page,rows );
        Page<Map> list=(Page)productMapper.findAllProduct (params);
        return list ;
    }
}
