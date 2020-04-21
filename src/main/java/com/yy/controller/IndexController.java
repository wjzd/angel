package com.yy.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

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
                                      @RequestParam(value = "reecom",defaultValue = "",required = false) String reecom,Model model,
                                      @RequestParam(value = "pageNum", defaultValue = "1",required = false) Integer pageNum,
                                      @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                      @RequestParam(value = "pageNum1", defaultValue = "1",required = false) Integer pageNum1,
                                      @RequestParam(value = "pageSize1", defaultValue = "10", required = false) Integer pageSize1,
                                      @RequestParam(value = "state",defaultValue = "",required = false)String state){
        PageInfo<Commodity> commodityPageInfo=null;
        PageInfo<Commodity> timeCommodity=null;
        categoryName="首页";
        //首页推荐
        commodityPageInfo= indexService.getCommodityList(categoryName, 1, pageNum, pageSize);
        //首页时间排序
        timeCommodity = indexService.getCommodityList(categoryName, 0, pageNum1, pageSize1);
        if (reecom.equals("1")){
            //首页推荐
            commodityPageInfo = indexService.getCommodityList(categoryName, 1, pageNum, pageSize);
        }else if (reecom.equals("0")){
            timeCommodity = indexService.getCommodityList(categoryName, 0, pageNum1, pageSize1);
        }
        model.addAttribute("tuijian", commodityPageInfo.getList());
        model.addAttribute("tuijiantotal", commodityPageInfo.getTotal());
        model.addAttribute("tuijianpageNum", commodityPageInfo.getPageNum());
        model.addAttribute("tuijianpageSize", commodityPageInfo.getPageSize());
        model.addAttribute("tuijianreecom", 1);
        model.addAttribute("tuijianCategoryName", categoryName);
        model.addAttribute("times", timeCommodity.getList());
        model.addAttribute("timetotal", timeCommodity.getTotal());
        model.addAttribute("timepageNum1", timeCommodity.getPageNum());
        model.addAttribute("timepageSize1", timeCommodity.getPageSize());
        model.addAttribute("timereecom", 0);
        model.addAttribute("timeCategoryName", categoryName);
        model.addAttribute("state",state);
        categoryName1="首页";
        model.addAttribute("categoryName",categoryName1);

        return "index";
    }
    @RequestMapping("/errorPage")
    public String error(){
        return"/page/404";
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

    @RequestMapping(value = "uploadimage")
    @ResponseBody
    public void uploadImage(@RequestParam("upfile") MultipartFile upfile,HttpServletRequest request, HttpServletResponse response) {

        String result = null;
        System.out.println("文件上传");
        // 项目在容器中实际发布运行的根路径
        String realPath = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        String fileUrl="";
        realPath=realPath+ "static/ueditorImg/";
        System.out.println(realPath);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHMMSS");
        String dateString=simpleDateFormat.format(new Date());
        if (!upfile.isEmpty()) {
            Map<String, String> resObj = new HashMap<>();
            String name=upfile.getOriginalFilename();//文件上传的真实名称
            String suffixName=name.substring(name.lastIndexOf("."));
            String hash= Integer.toHexString(new Random().nextInt());
            String fileName=dateString+hash+suffixName;
            fileUrl="/static/ueditorImg/"+fileName;
            try {
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File(realPath, fileName)));
                out.write(upfile.getBytes());
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Map<String,String> map=new HashMap<>();
            map.put("state","SUCCESS");
            map.put("url",fileUrl);
            map.put("title",fileName);
            map.put("original","fileName");
            result = JSON.toJSONString(map);
            PrintWriter writer = null;
            try {
                writer = response.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            writer.write(result);
            writer.flush();
            writer.close();
            System.out.println("result : " + result);
            //return map;
        } else {
            //return null;
        }
    }
}
