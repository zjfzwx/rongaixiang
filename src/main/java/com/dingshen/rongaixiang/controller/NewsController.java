package com.dingshen.rongaixiang.controller;

import com.dingshen.rongaixiang.domain.Image;
import com.dingshen.rongaixiang.domain.News;
import com.dingshen.rongaixiang.service.ImageService;
import com.dingshen.rongaixiang.service.NewsService;
import com.github.pagehelper.Page;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class NewsController {
    @Autowired
    NewsService newsService;
    @Autowired
    ImageService imageService;
    @ResponseBody
    @RequestMapping("/newsList")
    public Map newsList(Integer page, Integer limit, String type, String keyword) {
        Page<Map> newsInfos= newsService.findAllNews (page,limit,type,keyword );
        Map result=new HashMap();
        result.put("code" , 0);
        result.put("msg" , "");
        result.put("count" , newsInfos.getTotal ());
        result.put("data" , newsInfos.getResult ());
        return result;
    }
    @ResponseBody
    @RequestMapping("/getAllNews")
    public List<Map> getAllNews() {
       return newsService.getAllNews();
    }
    @RequestMapping("/getNewsById")
    public ModelAndView getNewsById(Integer id,ModelAndView modelAndView){
        News news= newsService.selectByPrimaryKey(id);
        if (news.getImageName()!=null&&!news.getImageName().equals("")){
            String[] urls=news.getImageName().split(",");
            Integer[] integers = (Integer[]) ConvertUtils.convert(urls, Integer.class);
            List<String> stringList=new ArrayList<>();
            for (int i=0;i<integers.length;i++){
                stringList.add(imageService.selectByPrimaryKey(integers[i]).getImgName());
            }
            modelAndView.addObject("stringList",stringList);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dt = simpleDateFormat.format(news.getReleaseTime());
        modelAndView.addObject("dt",dt);
        modelAndView.addObject("news",news);
        modelAndView.setViewName("news-single");
        return modelAndView;
    }
    @RequestMapping("/newsAdd")
    @ResponseBody
    public String  newsAdd(News news,String imgUrls){
        if (!imgUrls.equals("")&&imgUrls!=null) {
            String[] urls = imgUrls.split(",");
            StringBuffer imgName = new StringBuffer();
            Image image = new Image();
            for (int i = 0; i < urls.length; i++) {
                image.setImgName(urls[i]);
                imageService.insert(image);
                Integer imageId = imageService.findIdByName(urls[i]);
                imgName.append(imageId + ",");
            }
            news.setImageName(imgName.toString());
        }
        news.setReleaseTime(new Date());
        newsService.insert(news);
        return "1";
    }
    @RequestMapping("/newsUpdate")
    @ResponseBody
    public String updateNews(News news, HttpSession session,String imgUrls){
        if (!imgUrls.equals("")&&imgUrls!=null) {
            String[] urls = imgUrls.split(",");
            Image image = new Image();
            StringBuffer imgName=new StringBuffer();
            for (int i = 0; i < urls.length; i++) {
                image.setImgName(urls[i]);
                imageService.insert(image);
                Integer imageId = imageService.findIdByName(urls[i]);
                imgName.append(imageId + ",");
            }
            //News news1=newsService.selectByPrimaryKey(news.getId());
            String str=news.getImageName();
            if (str==null){
                news.setImageName(imgName.toString());
               newsService.updateByPrimaryKey(news);
            }else {
                String[] strs = str.split(",");
                Integer[] aftIdArray = (Integer[])ConvertUtils.convert(strs, Integer.class);
                for (int j=0;j<aftIdArray.length;j++){
                    imageService.deleteByPrimaryKey(aftIdArray[j]);
                }
                news.setImageName(imgName.toString());
            }
        }
        String loginUser= (String) session.getAttribute("loginUser");
        news.setModifier(loginUser);
        news.setUpdateTime(new Date());
        newsService.updateByPrimaryKey(news);
        return "1";
    }
    @RequestMapping("/newsDeleteById")
    @ResponseBody
    public Map deleteNewsById(Integer uid){
        newsService.deleteByPrimaryKey(uid);
        Map result = new HashMap();
        result.put("code" , 0);//0代表成功 其他代表失败
        result.put("msg" , uid + "内容已被删除"); //消息内容
        return result;
    }

    //跳转
    @RequestMapping("/toNewsList")
    public ModelAndView toNewsList(ModelAndView modelAndView){
        modelAndView.setViewName("news/list");
        return modelAndView;
    }
    @RequestMapping("/toNewsAdd")
    public ModelAndView toNewsAdd(ModelAndView modelAndView){
        modelAndView.setViewName("news/add");
        return modelAndView;
    }
    @RequestMapping("/toNewsUpdate")
    public String toNewsUpdate(Model model, Integer uid){
        News updateNews=newsService.selectByPrimaryKey(uid);
        if (updateNews.getImageName()!=null&&!updateNews.getImageName().equals("")){
            String[] urls = updateNews.getImageName().split(",");
            StringBuffer sbf=new StringBuffer();
            //将String数组转换为Integer数组
            Integer[] integers = (Integer[]) ConvertUtils.convert(urls, Integer.class);
            for (int i=0;i<integers.length;i++){
                Image image=imageService.selectByPrimaryKey(integers[i]);
                sbf.append(image.getImgName()+",");
            }
            String[] imageName = sbf.toString().split(",");
            model.addAttribute("imageName",imageName);
        }
        model.addAttribute ("updateNews",updateNews);
        return "news/update";
    }
    @RequestMapping("/toNewsShow")
    public String toNewsShow(Model model, Integer uid){
        News showNews=newsService.selectByPrimaryKey(uid);
        if (showNews.getImageName()!=null&&!showNews.getImageName().equals("")){
        String[] urls = showNews.getImageName().split(",");
        //将String数组转换为Integer数组
        Integer[] integers = (Integer[]) ConvertUtils.convert(urls, Integer.class);
        StringBuffer sbf=new StringBuffer();
        for (int i=0;i<integers.length;i++){
            Image image=imageService.selectByPrimaryKey(integers[i]);
            sbf.append(image.getImgName()+",");
        }
        String[] imageName = sbf.toString().split(",");
        model.addAttribute("imageName",imageName);
        }
        model.addAttribute ("showNews",showNews);
        return "news/show";
    }

}
