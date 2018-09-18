package com.dingshen.rongaixiang.controller;

import com.dingshen.rongaixiang.domain.User;
import com.dingshen.rongaixiang.service.UserService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @ResponseBody
    @RequestMapping("/userList")
    public Map userList(Integer page, Integer limit, String keyword) {
        Page<Map> userInfos= userService.findAllUser (page,limit,keyword );
        Map result=new HashMap();
        result.put("code" , 0);
        result.put("msg" , "");
        result.put("count" , userInfos.getTotal ());
        result.put("data" , userInfos.getResult ());
        return result;
    }
    @ResponseBody
    @RequestMapping("/getAllUser")
    public List<Map> getAllUser(){
        List<Map> userList=userService.getAllUser();
        return userList;
    }
    @RequestMapping("/UserSelectByPrimaryKey")
    public User selectByPrimaryKey(Integer id){
        return userService.selectByPrimaryKey(id);
    }
    @RequestMapping("/userAdd")
    @ResponseBody
    public Map createUser(User user){
        Map parm=new HashMap();
        parm.put("success","新增成功");
        parm.put("error","新增失败");
        userService.insert(user);
        return parm;
    }
    @RequestMapping("/userUpdate")
    @ResponseBody
    public Map updateUser(User user){
        Map parm=new HashMap();
        parm.put("success","修改成功");
        parm.put("error","修改失败");
        userService.updateByPrimaryKey(user);
        return parm;
    }
    @RequestMapping("/userDeleteById")
    @ResponseBody
    public Map deleteById(Integer uid){
        userService.deleteByPrimaryKey(uid);
        Map result = new HashMap();
        result.put("code" , 0);//0代表成功 其他代表失败
        result.put("msg" , uid + "内容已被删除"); //消息内容
        return result;
    }
    //跳转
    @RequestMapping("/toUserList")
    public ModelAndView toUserList(ModelAndView modelAndView){
        modelAndView.setViewName("user/list");
        return modelAndView;
    }
    @RequestMapping("/toUserAdd")
    public ModelAndView toUserAdd(ModelAndView modelAndView){
        modelAndView.setViewName("user/add");
        return modelAndView;
    }
    @RequestMapping("/toUserUpdate")
    public String toUserUpdate(Model model, Integer uid){
        User upuser=userService.selectByPrimaryKey(uid);
         model.addAttribute ("upuser",upuser);
        return "user/update";
    }

}
