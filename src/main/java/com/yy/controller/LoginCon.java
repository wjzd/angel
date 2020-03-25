package com.yy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginCon {

//    @RequestMapping(value="/")
//    public String index(){
//        System.out.println("999999");
//        return "login";
//    }
    @RequestMapping(value="/login")
    public  String login(){
        System.out.println("8888");
        return "/views/index";
    }
    @RequestMapping("/admin")
    @ResponseBody
    public String hello(){
        return "hello admin";
    }

    @RequestMapping(value="/console")
    public  String console(){
        System.out.println("777");
        return "/views/home/console";
    }
    @RequestMapping(value="/homepage1")
    public  String homepage1(){
        System.out.println("777");
        return "/views/home/homepage1";
    }
    @RequestMapping(value="/homepage2")
    public  String homepage2(){
        System.out.println("777");
        return "/views/home/homepage2";
    }
}
