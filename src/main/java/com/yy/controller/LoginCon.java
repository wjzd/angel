//package com.yy.controller;
//
//import com.yy.pojo.UserInfo;
//import com.yy.service.UserService;
//import com.yy.utils.MD5Util;
//import jdk.nashorn.internal.ir.annotations.Reference;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.List;
//
//@Controller
//@RequestMapping("/LoginCon")
//public class LoginCon {
//
//    @Reference
//    private UserService userService;
////    @RequestMapping(value="/")
////    public String index(){
////        System.out.println("999999");
////        return "login";
////    }
//    @RequestMapping(value="/login")
//    public  String login(){
//        System.out.println("8888");
//        return "/views/index";
//    }
//    @RequestMapping("/admin")
//    @ResponseBody
//    public String hello(){
//        return "hello admin";
//    }
//
//    @RequestMapping(value="/console")
//    public  String console(){
//        System.out.println("777");
//        return "/views/home/console";
//    }
//    @RequestMapping(value="/homepage1")
//    public  String homepage1(){
//        System.out.println("777");
//        return "/views/home/homepage1";
//    }
//    @RequestMapping(value="/homepage2")
//    public  String homepage2(){
//        System.out.println("777");
//        return "/views/home/homepage2";
//    }
//    //注册时判断账号注册过没有
//    @RequestMapping("/registerJudge")
//    @ResponseBody
//    public void registerJudge(UserInfo userInfo,HttpServletResponse resp,HttpServletRequest req) throws JSONException {
//
//
//        String error="0";
//        List<UserInfo> userList=userService.selectByarb(userInfo);
//        if(userList.size()>0){//已经注册
//            error="1";
//        }else{
//            error="0";
//        }
//
//        JSONObject json=new JSONObject();
//        json.put("error",error);
//
//        PrintWriter out=null;
//        try {
//            out=resp.getWriter();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        out.print(json);
//        out.flush();
//        out.close();
//
//    }
//
//    //注册
//    @RequestMapping("/registerUser")
//    public void registerUser(UserInfo userInfo){
//
//        String passwd= MD5Util.string2MD5(userInfo.getPasswd());
//        userInfo.setPasswd(passwd);
//        userService.insertSelective(userInfo);
//
//    }
//    //登录
//    @RequestMapping("/loginUser")
//    @ResponseBody
//    public void loginUser(HttpServletResponse resp, HttpServletRequest request,UserInfo userInfo) throws JSONException {
//
//        String error="0";
//        HttpSession session = request.getSession();
//
//        String passwd= MD5Util.string2MD5(userInfo.getPasswd());
//        userInfo.setPasswd(passwd);
//        List<UserInfo> userList=userService.selectByarb(userInfo);
//
//        if(userList.size()>0){
//            error="0";
//            userInfo=userList.get(0);
//            session.setAttribute("userInfo", userInfo);
//        }else{//账号或密码错误
//            error="1";
//        }
//
//        JSONObject json=new JSONObject();
//        json.put("error",error);
//
//        PrintWriter out=null;
//        try {
//            out=resp.getWriter();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        out.print(json);
//        out.flush();
//        out.close();
//
//    }
//}
