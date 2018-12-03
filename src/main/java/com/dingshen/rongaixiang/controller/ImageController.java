package com.dingshen.rongaixiang.controller;

import com.dingshen.rongaixiang.service.ImageService;
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
    ImageService imageService;
    @RequestMapping("/findAllImage")
    @ResponseBody
    public List<Map> findAllImage(Model model){
        List<Map> imageList=imageService.findAllImage(0,24);
        model.addAttribute("imageList",imageList);
        return imageList;
    }
    @RequestMapping("/")
    @ResponseBody
    public ModelAndView index(ModelAndView modelAndView, Integer currentPage, Integer limit){
        if (currentPage==null){
         currentPage=0;
        }
        if (limit==null){
            limit=24;
        }
        List<Map> imageList=imageService.findAllImage(currentPage,limit);
        modelAndView.addObject("imageList",imageList);
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
