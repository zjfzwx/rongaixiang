package com.dingshen.rongaixiang.controller;

import com.dingshen.rongaixiang.mapper.ImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class ImageController {
    @Autowired
    ImageMapper imageMapper;
    @RequestMapping("/findAllImage")
    @ResponseBody
    public List<Map> findAllImage(Model model){
        List<Map> imageList=imageMapper.findAllImage();
        model.addAttribute("imageList",imageList);
        return imageList;
    }
    @RequestMapping("/")
    @ResponseBody
    public ModelAndView index(ModelAndView modelAndView){
        List<Map> imageList=imageMapper.findAllImage();
        modelAndView.addObject("imageList",imageList);
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
