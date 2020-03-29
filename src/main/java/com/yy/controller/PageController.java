package com.yy.controller;

import com.yy.pojo.Commodity;
import com.yy.service.CommodityService;
import com.yy.service.PageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * 控制页面跳转Controller
 */
@Controller
@RequestMapping("/page")
public class PageController {

    @Resource
    private PageService pageService;
    @Resource
    private CommodityService commodityService;

    @RequestMapping("/getCommodity")
    public String getCommodity(Commodity commodity, Model model){
        //根据分类查询出编辑精选的商品
       //List<Commodity> jinxuan=pageService.getListbyCategoryIdAndreecom(id);
        List<Commodity> jinxuan=commodityService.selectByCom(commodity);
        //根据分类查询出最新的商品
       //List<Commodity> zuixin=pageService.getListByTime(id);
        List<Commodity> zuixin=commodityService.selectByCom(commodity);
        System.out.println("1111");
        return "/page/commodity";
    }
}
