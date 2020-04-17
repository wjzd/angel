package com.yy.controller;

import com.github.pagehelper.PageInfo;
import com.yy.pojo.Collect;
import com.yy.pojo.Commodity;
import com.yy.pojo.UserInfo;
import com.yy.service.CommodityService;
import com.yy.service.IndexService;
import com.yy.service.PageService;
import com.yy.utils.MD5Util;
import jdk.nashorn.internal.ir.IfNode;
import lombok.SneakyThrows;
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
import javax.servlet.http.HttpSession;
import java.io.File;
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

    @RequestMapping("getCommodity")
    public String getCommodity(@RequestParam(value = "categoryName",defaultValue = "",required = false)String categoryName,
                               @RequestParam(value = "reecom",defaultValue = "",required = false) String reecom,Model model, @RequestParam(value = "pageNum", defaultValue = "1",required = false) Integer pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "40", required = false) Integer pageSize,@RequestParam(value = "state",defaultValue = "",required = false)String state){
        PageInfo<Commodity> commodityPageInfo=null;
        PageInfo<Commodity> timeCommodity=null;
        if (reecom!="") {
            //首页推荐
            commodityPageInfo= indexService.getCommodityList(categoryName, 2, pageNum, pageSize);
            //首页时间排序
            timeCommodity = indexService.getCommodityList(categoryName, 0, pageNum, pageSize);

        }else if (reecom.equals("2")){
            //首页推荐
            commodityPageInfo = indexService.getCommodityList(categoryName, 2, pageNum, pageSize);

        }else if (reecom.equals("0")){
            timeCommodity = indexService.getCommodityList(categoryName, 0, pageNum, pageSize);
        }

        model.addAttribute("tuijian", commodityPageInfo.getList());
        model.addAttribute("tuijiantotal", commodityPageInfo.getTotal());
        model.addAttribute("tuijianpageNum", commodityPageInfo.getPageNum());
        model.addAttribute("tuijianpageSize", commodityPageInfo.getPageSize());
        model.addAttribute("tuijianreecom", 2);
        model.addAttribute("tuijianCategoryName", categoryName);
        model.addAttribute("times", timeCommodity.getList());
        model.addAttribute("timetotal", timeCommodity.getTotal());
        model.addAttribute("timepageNum", timeCommodity.getPageNum());
        model.addAttribute("timepageSize", timeCommodity.getPageSize());
        model.addAttribute("timereecom", 0);
        model.addAttribute("timeCategoryName", categoryName);
        model.addAttribute("state",state);
        categoryName1=categoryName;
        if (categoryName.equals("开通会员")){
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
        model.addAttribute("commodity",commodity);
        return "/page/commodityDetail";
    }

}

