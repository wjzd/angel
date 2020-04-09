package com.yy.controller;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yy.pojo.CategoryMenu;
import com.yy.pojo.Commodity;
import com.yy.pojo.ResultTable;
import com.yy.service.CategoryMennService;
import com.yy.service.CommodityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    @Resource
    private CategoryMennService categoryMennService;

    @RequestMapping("/toCommodity")
    public String toCommodity(){
        return "/views/commodity";
    }

    @RequestMapping("/getCommodityList")
    @ResponseBody
    public String getList(ResultTable resultTable,@RequestParam(value="pn",defaultValue="1") Integer pn,Commodity commodity){
        //获取第1页，5条内容，默认查询总数count
        /* 第一个参数是第几页；第二个参数是每页显示条数 */
        PageHelper.startPage(pn, 4);
        List<Commodity> commodityList=commodityService.selectByCom(null);
        resultTable.setCode(0);
        resultTable.setData(commodityList);
        resultTable.setCount(commodityList.size());
        return JSON.toJSONString(resultTable);
    }
    @RequestMapping("/addCommodityInit")
    public String addCommodityInit(Model model,Commodity commodity){
        //商品分類
        List<CategoryMenu> cateList=categoryMennService.selectAll(null);
        if(commodity.getId()!=null){
            commodity=commodityService.selectByCom(commodity).get(0);
        }
        model.addAttribute("cateList",cateList);
        model.addAttribute("commodity",commodity);
        return "/views/addCommodity";
    }
    @RequestMapping("/addCommodity")
    public String addCommodity(Model model,Commodity commodity){
        try {
            if(commodity.getId()!=null){//修改
                int count=commodityService.updateByPrimaryKeySelective(commodity);
                System.out.println("修改："+count);
            }else{
                commodityService.insertSelective(commodity);
            }

        }catch (Exception e){
            e.printStackTrace();
        }


        return "/views/commodity";
    }
    @RequestMapping("/updateCommodityInit")
    public String updateCommodityInit(Model model,Commodity commodity){

        //商品分類
        List<CategoryMenu> cateList=categoryMennService.selectAll(null);
        if(commodity.getId()!=null){
            commodity=commodityService.selectByCom(commodity).get(0);
        }

        model.addAttribute("cateList",cateList);
        model.addAttribute("commodity",commodity);

        return "/views/addCommodity";
    }

    @RequestMapping("/commodityView")
    public String commodityView(Model model,Commodity commodity){
        if(commodity.getId()!=null){
            commodity=commodityService.selectByCom(commodity).get(0);
        }

        model.addAttribute("commodity",commodity);
        return "/views/commodityView";
    }
}
