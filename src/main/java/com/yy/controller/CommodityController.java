package com.yy.controller;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.yy.pojo.Commodity;
import com.yy.pojo.ResultTable;
import com.yy.service.CommodityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.awt.*;
import java.util.List;

@Controller
@RequestMapping("/views")
public class CommodityController {

    @Resource
    private CommodityService commodityService;

    @RequestMapping("/toCommodity")
    public String toCommodity(){
        return "/views/commodity";
    }

    @RequestMapping("/getCommodityList")
    @ResponseBody
    public String getList(ResultTable resultTable,@RequestParam(value="pn",defaultValue="1") Integer pn,Commodity commodity){
        //获取第1页，5条内容，默认查询总数count
        /* 第一个参数是第几页；第二个参数是每页显示条数 */
        PageHelper.startPage(pn, 8);

        List<Commodity> commodityList=commodityService.selectByCom(null);
        resultTable.setCode(0);
        resultTable.setData(commodityList);
        resultTable.setCount(commodityList.size());
        return JSON.toJSONString(resultTable);
    }
    @RequestMapping("/addCommodity")
    public String addCommodity(){
        return "/views/addCommodity";
    }
}
