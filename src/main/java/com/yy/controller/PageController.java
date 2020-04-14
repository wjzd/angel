package com.yy.controller;

import com.github.pagehelper.PageInfo;
import com.yy.pojo.Collect;
import com.yy.pojo.Commodity;
import com.yy.pojo.UserInfo;
import com.yy.service.PageService;
import jdk.nashorn.internal.ir.IfNode;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
        if (category.equals("开通会员")){
            return "/page/vipPage";
        }
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
    @RequestMapping("/center")
    public String userCenter(HttpServletRequest request,Model model,@RequestParam(value = "state",defaultValue = "1",required = false)String state,
                             @RequestParam(value = "pageNum", defaultValue = "1",required = false) Integer pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize){
        HttpSession session = request.getSession();
        UserInfo userInfo= (UserInfo) session.getAttribute("userInfo");
        if (userInfo==null){
            return "/page/login";
        }else {
            PageInfo<Commodity> commodityPageInfo=pageService.getCommodityByuserId(userInfo.getId(),pageNum,pageSize);
            model.addAttribute("total",commodityPageInfo.getTotal());
            model.addAttribute("pageSize",commodityPageInfo.getPageSize());
            model.addAttribute("pageNum",commodityPageInfo.getPageNum());
            model.addAttribute("state",state);
            model.addAttribute("userInfo",userInfo);
        }
        return "/page/userCenter";
    }
}
