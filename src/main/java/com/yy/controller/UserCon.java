package com.yy.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.yy.pojo.Commodity;
import com.yy.pojo.ResultTable;
import com.yy.pojo.UserInfo;
import com.yy.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/userCon")
public class UserCon {

    @Resource
    private UserService userService;

    @RequestMapping("/toUserlist")
    public String toUserlist(){
        return "/views/userList";
    }

    @RequestMapping("/userList")
    @ResponseBody
    public String userList(ResultTable resultTable, @RequestParam(value="pn",defaultValue="1") Integer pn, UserInfo userInfo){

        //获取第1页，5条内容，默认查询总数count
        /* 第一个参数是第几页；第二个参数是每页显示条数 */
        PageHelper.startPage(pn, 8);

        List<UserInfo> userList=userService.selectByarb(userInfo);
        resultTable.setCode(0);
        resultTable.setData(userList);
        resultTable.setCount(userList.size());
        return JSON.toJSONString(resultTable);
    }
    @RequestMapping("/userAddInit")
    public String userAddInit(Model model,UserInfo userInfo){

        if(userInfo.getId()!=null){
            userInfo=userService.selectByarb(userInfo).get(0);

            model.addAttribute("userInfo",userInfo);
        }
        return "/views/userAdd";
    }

    @RequestMapping("/userAdd")
    public String userAdd(Model model,UserInfo userInfo){


        if(userInfo.getId()!=null){
            userService.updateByPrimaryKeySelective(userInfo);
        }else{
            userService.insertSelective(userInfo);
        }
        return "/views/userList";
    }

}
