package com.yy.controller;


import com.alibaba.fastjson.JSON;
import com.yy.pojo.Commodity;
import com.yy.pojo.ResultTable;
import com.yy.service.CommodityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
        return "views/commodity";
    }

    @RequestMapping("/getCommodityList")
    @ResponseBody
    public String getList(ResultTable resultTable){
        List<Commodity> commodityList=commodityService.selectByCom(null);
        resultTable.setCode(0);
        resultTable.setData(commodityList);
        resultTable.setCount(commodityList.size());
        return JSON.toJSONString(resultTable);
    }
}
