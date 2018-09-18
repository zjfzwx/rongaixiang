package com.dingshen.rongaixiang.controller;

import com.dingshen.rongaixiang.domain.Job;
import com.dingshen.rongaixiang.service.JobService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class JobController {
    @Autowired
    JobService jobService;
    @ResponseBody
    @RequestMapping("/jobList")
    public Map jobList(Integer page, Integer limit, String type, String keyword) {
        Page<Map> userInfos= jobService.findAllJob (page,limit,type,keyword );
        Map result=new HashMap();
        result.put("code" , 0);
        result.put("msg" , "");
        result.put("count" , userInfos.getTotal ());
        result.put("data" , userInfos.getResult ());
        return result;
    }
    @ResponseBody
    @RequestMapping("/getJobList")
    public List<Map> getJobList() {
        return jobService.getJobList();
    }
    @RequestMapping("/getJobById")
    public Job getJobById(Integer id){
        return jobService.selectByPrimaryKey(id);
    }
    @RequestMapping("/jobAdd")
    @ResponseBody
    public Map createJob(Job job){
        Map parm=new HashMap();
        parm.put("success","新增成功");
        parm.put("error","新增失败");
        job.setCreateTime(new Date());
        jobService.insert(job);
        return parm;
    }
    @RequestMapping("/jobUpdate")
    @ResponseBody
    public Map updateJob(Job job){
        jobService.updateByPrimaryKey(job);
        Map parm=new HashMap();
        parm.put("success","修改成功");
        parm.put("error","修改失败");
        return parm;
    }
    @RequestMapping("/jobDeleteById")
    @ResponseBody
    public Map deleteJobById(Integer uid){
        jobService.deleteByPrimaryKey(uid);
        Map result = new HashMap();
        result.put("code" , 0);//0代表成功 其他代表失败
        result.put("msg" , uid + "内容已被删除"); //消息内容
        return result;
    }

    //跳转
    @RequestMapping("/toJobList")
    public ModelAndView toJobList(ModelAndView modelAndView){
        modelAndView.setViewName("job/list");
        return modelAndView;
    }
    @RequestMapping("/toJobAdd")
    public ModelAndView toApplicationAdd(ModelAndView modelAndView){
        modelAndView.setViewName("job/add");
        return modelAndView;
    }
    @RequestMapping("/toJobUpdate")
    public String toJobUpdate(Model model, Integer uid){
        Job updateJob=jobService.selectByPrimaryKey(uid);
        model.addAttribute ("updateJob",updateJob);
        return "job/update";
    }
}


