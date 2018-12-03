package com.dingshen.rongaixiang.controller;

import com.dingshen.rongaixiang.domain.User;
import com.dingshen.rongaixiang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    UserService userService;
    @RequestMapping("/toMap")
    public String toMap(){
        return "map1";
    }
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/loginOut")
    public String loginOut(){
        return "login";
    }
    @RequestMapping("/logincheck")
    public ModelAndView logincheck(User user, ModelAndView modelAndView, HttpSession session){
        if (userService.checkUserName(user)){
            modelAndView.addObject("username",user.getUsername());
            session.setAttribute("loginUser",user.getUsername());
          modelAndView.setViewName("admin");
        }else {
            modelAndView.addObject("message","用户名或密码错误");
            modelAndView.setViewName("login");
        }
        return modelAndView;
    }
}
