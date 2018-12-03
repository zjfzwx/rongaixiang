package com.dingshen.rongaixiang.controller;

import com.dingshen.rongaixiang.domain.Contact;
import com.dingshen.rongaixiang.service.ContactService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ContactController {
    @Autowired
    ContactService contactService;
    @ResponseBody
    @RequestMapping("/contactList")
    public Map contactList(Integer page, Integer limit, String type, String keyword) {
        Page<Map> userInfos= contactService.findAllContact (page,limit,type,keyword );
        Map result=new HashMap();
        result.put("code" , 0);
        result.put("msg" , "");
        result.put("count" , userInfos.getTotal ());
        result.put("data" , userInfos.getResult ());
        return result;
    }
    @RequestMapping("/toContactShow")
    public ModelAndView toContactShow(ModelAndView modelAndView,Integer uid){
        Contact contact=contactService.selectByPrimaryKey(uid);
        modelAndView.addObject("contact",contact);
        modelAndView.setViewName("contact/show");
        return modelAndView;
    }
    @RequestMapping("/getContactById")
    public Contact getContactById(Integer id){
        return contactService.selectByPrimaryKey(id);
    }
    @RequestMapping("/contactAdd")
    @ResponseBody
    public Map createContact(Contact contact){
        Map parm=new HashMap();
        parm.put("success","新增成功");
        parm.put("error","新增失败");
        contactService.insert(contact);
        return parm;
    }

    @RequestMapping("/contactDeleteById")
    @ResponseBody
    public Map deleteContactById(Integer uid){
        contactService.deleteByPrimaryKey(uid);
        Map result = new HashMap();
        result.put("code" , 0);//0代表成功 其他代表失败
        result.put("msg" , uid + "内容已被删除"); //消息内容
        return result;
    }

    //跳转
    @RequestMapping("/toContactList")
    public ModelAndView toJobList(ModelAndView modelAndView){
        modelAndView.setViewName("contact/list");
        return modelAndView;
    }

}


