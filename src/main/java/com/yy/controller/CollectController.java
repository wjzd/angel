package com.yy.controller;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yy.pojo.Collect;
import com.yy.pojo.ResultTable;
import com.yy.pojo.UserInfo;
import com.yy.service.CollectService;
import com.yy.service.CommodityService;
import com.yy.utils.MD5Util;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

@Controller
@RequestMapping("/collectCon")
public class CollectController {

    @Resource
    private CollectService collectService;
    @RequestMapping("/toCollectList")
    public String toCollectList(){
        return "/views/collectList";
    }

    @RequestMapping(value = "/collectList",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String collectList(ResultTable resultTable, @RequestParam("limit")int limit, @RequestParam("page")int page ,Collect collect){

        //获取第1页，5条内容，默认查询总数count
        /* 第一个参数是第几页；第二个参数是每页显示条数 */
        String orderBy="collectTime desc";
        PageHelper.startPage(page,limit,orderBy);
        List<Collect> collectList=collectService.selectCollect(collect);
        PageInfo<Collect> pageInfo=new PageInfo(collectList);
        resultTable.setCode(0);
        resultTable.setData(pageInfo.getList());
        resultTable.setCount((int)pageInfo.getTotal());
        return JSON.toJSONString(resultTable);
    }



}
