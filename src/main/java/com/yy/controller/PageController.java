package com.yy.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.yy.pojo.Collect;
import com.yy.pojo.Commodity;
import com.yy.pojo.DownloanInfo;
import com.yy.pojo.UserInfo;
import com.yy.service.*;
import com.yy.utils.MD5Util;
import jdk.nashorn.internal.ir.IfNode;
import lombok.SneakyThrows;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yy.controller.IndexController.categoryName1;

/**
 * 控制页面跳转Controller
 */
@Controller
@RequestMapping("page")
public class PageController {

    @Resource
    private PageService pageService;
    @Resource
    private IndexService indexService;
    @Resource
    private CommodityService commodityService;
    @Resource
    private DownloanService downloanService;
    @Resource
    private CollectService collectService;

    @RequestMapping("getCommodity")
    public String getCommodity(@RequestParam(value = "categoryName",defaultValue = "",required = false)String categoryName,
                               @RequestParam(value = "reecom",defaultValue = "",required = false) String reecom,Model model,
                               @RequestParam(value = "pageNum", defaultValue = "1",required = false) Integer pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "40", required = false) Integer pageSize,
                               @RequestParam(value = "pageNum1", defaultValue = "1",required = false) Integer pageNum1,
                               @RequestParam(value = "pageSize1", defaultValue = "10", required = false) Integer pageSize1,
                               @RequestParam(value = "state",defaultValue = "",required = false)String state){
        PageInfo<Commodity> commodityPageInfo=null;
        PageInfo<Commodity> timeCommodity=null;
        //首页推荐
        commodityPageInfo= indexService.getCommodityList(categoryName, 2, pageNum, pageSize);
        //首页时间排序
        timeCommodity = indexService.getCommodityList(categoryName, 0, pageNum1, pageSize1);
        if (reecom.equals("2")){
            //首页推荐
            commodityPageInfo = indexService.getCommodityList(categoryName, 2, pageNum, pageSize);

        }else if (reecom.equals("0")){
            timeCommodity = indexService.getCommodityList(categoryName, 0, pageNum1, pageSize1);
        }

        model.addAttribute("tuijian", commodityPageInfo.getList());
        model.addAttribute("tuijiantotal", commodityPageInfo.getTotal());
        model.addAttribute("tuijianpageNum", commodityPageInfo.getPageNum());
        model.addAttribute("tuijianpageSize", commodityPageInfo.getPageSize());
        model.addAttribute("tuijianreecom", 2);
        model.addAttribute("tuijianCategoryName", categoryName);
        model.addAttribute("times", timeCommodity.getList());
        model.addAttribute("timetotal", timeCommodity.getTotal());
        model.addAttribute("timepageNum1", timeCommodity.getPageNum());
        model.addAttribute("timepageSize1", timeCommodity.getPageSize());
        model.addAttribute("timereecom", 0);
        model.addAttribute("timeCategoryName", categoryName);
        model.addAttribute("state",state);
        categoryName1=categoryName;
        if (categoryName.equals("开通会员")){
            return "/page/vipPage";
        }
        if (categoryName.equals("首页")){
            return "redirect:/";
        }
        return "/page/commodity";
    }
    @RequestMapping("/search")
    public String search(@RequestParam("name")String name,@RequestParam(value = "pageNum", defaultValue = "1",required = false) Integer pageNum,
                         @RequestParam(value = "pageSize", defaultValue = "40", required = false) Integer pageSize,Model model){
        PageInfo<Commodity> commodityPage=indexService.getSearch(name,pageNum,pageSize);
        model.addAttribute("search",commodityPage.getList());
        model.addAttribute("total",commodityPage.getTotal());
        model.addAttribute("pageNum",commodityPage.getPageNum());
        model.addAttribute("pageSize",commodityPage.getPageSize());
        model.addAttribute("name",name);
        categoryName1="首页";
        return "/page/search";
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
                             @RequestParam(value = "pageSize", defaultValue = "8", required = false) Integer pageSize){
        HttpSession session = request.getSession();
        UserInfo userInfo= (UserInfo) session.getAttribute("userInfo");
        String email=userInfo.getEmail();
        String resultEmail = email.replaceAll("(\\w?)(\\w+)(\\w)(@\\w+\\.[a-z]+(\\.[a-z]+)?)", "$1****$3$4");
        userInfo.setEmail(resultEmail);
        if (userInfo==null){
            return "/page/login";
        }else {
            PageInfo<Commodity> commodityPageInfo=pageService.getCommodityByuserId(userInfo.getId(),pageNum,pageSize);
            model.addAttribute("list",commodityPageInfo.getList());
            model.addAttribute("total",commodityPageInfo.getTotal());
            model.addAttribute("pageSize",commodityPageInfo.getPageSize());
            model.addAttribute("pageNum",commodityPageInfo.getPageNum());
            model.addAttribute("state",state);
            model.addAttribute("userInfo",userInfo);
            categoryName1="";
        }
        return "/page/userCenter";
    }
    @SneakyThrows
    @RequestMapping("/modifyHeadImg")
    @ResponseBody
    public Map<String,String> modifyHeadImg(@RequestParam(value = "file") MultipartFile photo, HttpServletRequest request){
        UserInfo userInfo= (UserInfo) request.getSession().getAttribute("userInfo");
        Map<String, String> ret = new HashMap<String, String>();
        if (photo == null) {
            ret.put("type", "error");
            ret.put("msg", "选择要上传的文件！");
            return ret;
        }
        if (photo.getSize() > 1024 * 1024 * 10) {
            ret.put("type", "error");
            ret.put("msg", "文件大小不能超过10M！");
            return ret;
        }
        //获取文件后缀
        String suffix = photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf(".") + 1, photo.getOriginalFilename().length());
        if (!"jpg,jpeg,gif,png".toUpperCase().contains(suffix.toUpperCase())) {
            ret.put("type", "error");
            ret.put("msg", "请选择jpg,jpeg,gif,png格式的图片！");
            return ret;
        }
        //获取项目根目录加上图片目录 webapp/static/imgages/upload/
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
//      String savePath = request.getSession().getServletContext().getRealPath("/") + "/static/upload/";
        String savePath=path.getAbsolutePath()+"/static/upload/";
//        File savePath = new File(path.getAbsolutePath(),"static/upload/");
        File savePathFile = new File(savePath);

        if (!savePathFile.exists()) {
            //若不存在该目录，则创建目录
            savePathFile.mkdir();
        }
        String filename = new Date().getTime() + "." + suffix;
        try {
            //将文件保存指定目录
            photo.transferTo(new File(savePath + filename));
        } catch (Exception e) {
            ret.put("type", "error");
            ret.put("msg", "保存文件异常！");
            e.printStackTrace();
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "上传图片成功！");
        ret.put("filepath", request.getSession().getServletContext().getContextPath() + "/static/upload/");
        ret.put("filename", filename);
        userInfo.setHeadimg("/static/upload/"+filename);
        pageService.updateUserInfo(userInfo);
        return ret;
    }
    @RequestMapping(value = "/getCommodityList",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getList(String type){
        if (type.equals("1")){
            List<Commodity> commodities=commodityService.selectByCom(null);
            return JSON.toJSONString(commodities);
        }else {
            return "/error";
        }

    }
    @RequestMapping("/updateUserInfo")
    @ResponseBody
    public Map<String,String> updateUserInfo(@RequestParam(value = "password1",required = false)String password,
                                             @RequestParam(value = "account",required = false)String account,
                                             @RequestParam(value = "workername",required = false)String workername,
                                             @RequestParam(value = "email",required = false)String email,
                                             HttpServletRequest request){
        Map<String, String> ret = new HashMap<String, String>();
        HttpSession session=request.getSession();
        UserInfo user= (UserInfo) session.getAttribute("userInfo");
        if (password!=null && !password.equals("")){
            String passwd= MD5Util.string2MD5(password);
            user.setPasswd(passwd);
        }
        if (account!=null && !account.equals("")){
            user.setAccount(account);
        }
        if (workername!=null && !workername.equals("")){
            user.setWorkername(workername);
        }
        if (email!=null && !email.equals("")){
            user.setEmail(email);
        }
        int state=pageService.updateUserInfo(user);
        if (state!=1){
            ret.put("msg","修改信息失败");
            ret.put("type","error");
        }else {
            ret.put("msg","修改信息成功");
            ret.put("type","success");
        }
        return ret;
    }
    @RequestMapping("/tomodifyUserInfo")
    public String tomodifyUserInfo(@RequestParam(value = "modifyName",required = false)String modifyName,Model model){
        model.addAttribute("modifyName",modifyName);
        return "/page/modifyUserInfo";
    }
    @RequestMapping("/checkPassword")
    @ResponseBody
    public Boolean check(@RequestParam("password")String password,HttpServletRequest request){
        String md5=MD5Util.string2MD5(password);
        HttpSession session=request.getSession();
        UserInfo userInfo= (UserInfo) session.getAttribute("userInfo");
        if (userInfo.getPasswd().equals(md5)){
            return true;
        }else {
            return false;
        }
    }

    @RequestMapping("/commodityDetail")
    public String commodityDetail(Model model,Commodity commodity){
        commodity=commodityService.selectByCom(commodity).get(0);
        commodity.setBrowsenum(commodity.getBrowsenum()+1);
        commodityService.updateByPrimaryKeySelective(commodity);
        model.addAttribute("commodity",commodity);
        return "/page/commodityDetail";
    }
    //用户点击下载
    @RequestMapping("/download")
    @ResponseBody
    public void updateCollect(HttpServletResponse resp, HttpServletRequest request, Commodity commodity) throws JSONException {

        HttpSession session = request.getSession();

        List<Commodity> comList=commodityService.selectByCom(commodity);
        UserInfo userInfo= (UserInfo) session.getAttribute("userInfo");
        JSONObject json=new JSONObject();
        int type=0;
        if(userInfo.getIsvip()==1){//是VIp
            type=1;
            //判断用户是否下载过
            DownloanInfo dw=new DownloanInfo();
            dw.setComid(commodity.getId());
            dw.setUserid(userInfo.getId());
            List<DownloanInfo> downloanInfos=downloanService.selectByDown(dw);
            if(downloanInfos.size()>0){//已下载过修改
                dw.setDownnum(downloanInfos.get(0).getDownnum()+1);
                dw.setDowntime(new Date());
                downloanService.updateByPrimaryKeySelective(dw);
            }else{
                dw.setDownnum(1);
                dw.setDowntime(new Date());
                downloanService.insertSelective(dw);
            }
            json.put("comUrl",comList.get(0).getComurl());
            json.put("comCode",comList.get(0).getComCode());
            json.put("type",type);
        }else{
            type=0;
            json.put("type",type);
        }
        PrintWriter out=null;
        try {
            out=resp.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.print(json);
        out.flush();
        out.close();
    }
    //用户点击收藏
    @RequestMapping("/updateCollect")
    @ResponseBody
    public void updateCollect(HttpServletResponse resp, HttpServletRequest request, Collect collect) {

        try {


            HttpSession session = request.getSession();
            UserInfo userInfo= (UserInfo) session.getAttribute("userInfo");
            collect.setUserid(userInfo.getId());

            List<Collect> collectList=collectService.selectCollect(collect);
            int type=0;
            if(collectList.size()>0){//已经收藏则取消收藏
                collect=collectList.get(0);
                Integer num=collectService.deleteByPrimaryKey(collect);
                type=0;//已取消
            }else{//未收藏新增收藏
                collectService.insertSelective(collect);
                type=1;//已收藏
            }
            JSONObject json=new JSONObject();
            json.put("type",type);

            PrintWriter out=null;
            try {
                out=resp.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.print(json);
            out.flush();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

