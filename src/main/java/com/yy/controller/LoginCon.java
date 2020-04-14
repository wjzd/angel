package com.yy.controller;

import com.yy.pojo.UserInfo;
import com.yy.service.UserService;
import com.yy.utils.MD5Util;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/LoginCon")
public class LoginCon {

    @Resource
    private UserService userService;
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
    //注册时判断账号注册过没有
    @RequestMapping("/registerJudge")
    @ResponseBody
    public void registerJudge(UserInfo userInfo, HttpServletResponse resp, HttpServletRequest req,
                              @RequestParam(value = "account",defaultValue = "",required = false)String account,
                              @RequestParam(value = "email",defaultValue = "",required = false)String email) throws JSONException {
        String error="0";
        if (userInfo==null){
            userInfo=new UserInfo();
            if (!account.equals("")){
                userInfo.setAccount(account);
            }
            if (!email.equals("")){
                userInfo.setEmail(email);
            }
        }
        List<UserInfo> userList=userService.selectByarb(userInfo);
        if(userList!=null && userList.size()>0){//已经注册
            error="1";
        }else{
            error="0";
        }
        JSONObject json=new JSONObject();
        json.put("error",error);

        PrintWriter out=null;
        try {
            out=resp.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.print(json);
        out.flush();
        out.close();

    }

    //注册
    @RequestMapping("/registerUser")
    @ResponseBody
    public String  registerUser(UserInfo userInfo,  @RequestParam(value = "account")String account,
                             @RequestParam(value = "email",required = false)String email,
                             @RequestParam(value = "password",required = false)String password ){
        String error="0";
        if (userInfo==null){
            userInfo=new UserInfo();
        }
        userInfo.setAccount(account);
        userInfo.setEmail(email);
        userInfo.setPasswd(password);
        userInfo.setWorkername(UUID.randomUUID().toString().replaceAll("-","").substring(0,7));
        userInfo.setIsvip(0);
        String passwd= MD5Util.string2MD5(userInfo.getPasswd());
        userInfo.setPasswd(passwd);
        userInfo.setHeadimg("/static/img/1.png");
       try{
            userService.insertSelective(userInfo);
            error="1";
       }catch (Exception e){
            error="0";
       }
        return error;
    }
    //登录
    @RequestMapping("/loginUser")
    @ResponseBody
    public void loginUser(HttpServletResponse resp, HttpServletRequest request,UserInfo userInfo) throws JSONException {

        String error="0";
        HttpSession session = request.getSession();

        String passwd= MD5Util.string2MD5(userInfo.getPasswd());
        userInfo.setPasswd(passwd);
        List<UserInfo> userList=userService.selectByarb(userInfo);

        if(userList.size()>0){
            error="0";
            userInfo=userList.get(0);
            session.setAttribute("userInfo", userInfo);
        }else{//账号或密码错误
            userInfo.setEmail(userInfo.getAccount());
            userInfo.setAccount(null);
            List<UserInfo> userList1=userService.selectByarb(userInfo);
            if (userList1.size()>0){
                error="0";
                userInfo=userList1.get(0);
                session.setAttribute("userInfo", userInfo);
            }else {
                error="1";
            }
        }

        JSONObject json=new JSONObject();
        json.put("error",error);

        PrintWriter out=null;
        try {
            out=resp.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.print(json);
        out.flush();
        out.close();

    }
}
