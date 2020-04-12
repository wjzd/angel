package com.yy.controller;

import com.yy.pojo.Commodity;
import com.yy.service.PageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

import static com.yy.controller.IndexController.categoryName1;

/**
 * 控制页面跳转Controller
 */
@Controller
@RequestMapping("page")
public class PageController {

    @Resource
    private PageService pageService;

    @RequestMapping("getCommodity")
    public String getCommodity(String  category, Model model,@RequestParam(value="pn",defaultValue="1") Integer pn){
        //根据分类查询出编辑精选的商品
       List<Commodity> jinxuan=pageService.getListbyCategoryIdAndreecom(category);
        //根据分类查询出最新的商品
       List<Commodity> zuixin=pageService.getListByTime(category);
       model.addAttribute("jinxuans",jinxuan);
       model.addAttribute("zuixins",zuixin);
        categoryName1=category;
        return "/page/commodity";
    }
    @RequestMapping("login")
    public String login(){
        return "/page/login";
    }
    @RequestMapping("register")
    public String register(){
        return "/page/register";
    }
    @RequestMapping("/vip")
    public String vipPage(){
        return "/page/vipPage";
    }
}
