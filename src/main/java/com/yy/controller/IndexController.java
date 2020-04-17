package com.yy.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.yy.pojo.AdminUserInfo;
import com.yy.pojo.Commodity;
import com.yy.service.AdminUserInfoService;
import com.yy.service.IndexService;
import com.yy.service.PageService;
import com.yy.service.UserService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Resource
    private IndexService indexService;
    @Resource
    private PageService pageService;
    @Resource
    private AdminUserInfoService adminUserInfoService;

    public static String categoryName1="首页";

    @RequestMapping("/")
    public String getiIfarmeCommodity(@RequestParam(value = "categoryName",defaultValue = "",required = false)String categoryName,
                                      @RequestParam(value = "reecom",defaultValue = "",required = false) String reecom,Model model, @RequestParam(value = "pageNum", defaultValue = "1",required = false) Integer pageNum,
                                      @RequestParam(value = "pageSize", defaultValue = "40", required = false) Integer pageSize,@RequestParam(value = "state",defaultValue = "",required = false)String state){
        PageInfo<Commodity> commodityPageInfo=null;
        PageInfo<Commodity> timeCommodity=null;
        if (reecom=="") {
            //首页推荐
            commodityPageInfo= indexService.getCommodityList(categoryName, 1, pageNum, pageSize);
            //首页时间排序
            timeCommodity = indexService.getCommodityList(categoryName, 0, pageNum, pageSize);
        }else if (reecom.equals("1")){
            //首页推荐
            commodityPageInfo = indexService.getCommodityList(categoryName, 1, pageNum, pageSize);
        }else if (reecom.equals("0")){
            timeCommodity = indexService.getCommodityList(categoryName, 0, pageNum, pageSize);
        }
        model.addAttribute("tuijian", commodityPageInfo.getList());
        model.addAttribute("tuijiantotal", commodityPageInfo.getTotal());
        model.addAttribute("tuijianpageNum", commodityPageInfo.getPageNum());
        model.addAttribute("tuijianpageSize", commodityPageInfo.getPageSize());
        model.addAttribute("tuijianreecom", 1);
        model.addAttribute("tuijianCategoryName", categoryName);
        model.addAttribute("times", timeCommodity.getList());
        model.addAttribute("timetotal", timeCommodity.getTotal());
        model.addAttribute("timepageNum", timeCommodity.getPageNum());
        model.addAttribute("timepageSize", timeCommodity.getPageSize());
        model.addAttribute("timereecom", 0);
        model.addAttribute("timeCategoryName", categoryName);
        model.addAttribute("state",state);
        categoryName1="首页";
        model.addAttribute("categoryName",categoryName1);

        return "index";
    }

    @RequestMapping("/getHeader")
    public String header(Model model,HttpServletRequest request){
//        HttpSession session = request.getSession();
//        model.addAttribute("userInfo",request.getAttribute("userInfo"));
        model.addAttribute("menus",indexService.getMenuList());
        model.addAttribute("name",categoryName1);
        return "header";
    }
    @RequestMapping("/getFooter")
    public String footer(){
        return "footer";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session=request.getSession();
        session.removeAttribute("userInfo");
        return "redirect:/";
    }

    @RequestMapping("/ueditor")
    public String euditor(){
        return "ueditor/ueditor";
    }

    @RequestMapping("/viewLoginInit")
    public String viewLoginInit(){
        return "/views/login";
    }

    @RequestMapping("/viewLogin")
    public String viewLogin(Model mdoel, AdminUserInfo adminUserInfo,HttpServletResponse resp, HttpServletRequest request){
        try {
            List<AdminUserInfo> adminUserInfos=adminUserInfoService.selectByAdmin(adminUserInfo);
            HttpSession session = request.getSession();

            if(adminUserInfos.size()>0){
                adminUserInfo=adminUserInfos.get(0);
                session.setAttribute("adminUserInfo", adminUserInfo);
                return "redirect:/LoginCon/login";
            }else{
                return "redirect:/viewLoginInit";
            }

        }catch (Exception e){
            e.printStackTrace();
            return "redirect:/viewLoginInit";
        }

    }

}
