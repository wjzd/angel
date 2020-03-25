package com.yy.controller;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.yy.pojo.SysUser;
import com.yy.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/userCon")
public class UserCon {

    @Resource
    private UserService userService;


    @RequestMapping(value="/userList")

    public String userList(SysUser sysUser, HttpServletResponse resp){

        List<SysUser> userList=userService.userList(sysUser);

        return "/views/user/administrators/list";

    }
    @RequestMapping(value="/getuserList")
    @ResponseBody
    public void index(SysUser sysUser, HttpServletResponse resp){

        List<SysUser> userList=userService.userList(sysUser);

        System.out.println("集合长度："+userList.size());
        JSONObject result = new JSONObject();
        try {
            result.put("code", "0");
            result.put("msg", "HttpServletResponse");
            result.put("count", "100");
            result.put("data", userList);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
