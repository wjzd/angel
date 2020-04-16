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
        if (reecom!="") {
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
        @RequestMapping("/config")
        public void initController(MultipartFile upfile, HttpServletRequest request, HttpServletResponse response) {

            try {
                request.setCharacterEncoding("UTF-8");
                response.setHeader("Content-Type", "text/html");

                System.out.println("url : " + request.getRequestURL().toString());
                String value = request.getParameter("action");
                System.out.println("action:" + value);

                // 文件上传的路径
                String rootPath = "C:/Users/Administrator/Desktop/tomcat-8.5.38/webapps/ROOT/img/";
                String result = null;
                if ("config".equals(value)) { // 读取配置文件，将配置文件数据以json格式返回
                    String configPath = "/config.json";// 配置文件路径， 相对于classpath
                    /**
                     * 返回JOSN数据
                     * {"videoMaxSize":102400000,"videoActionName":"uploadvideo","fileActionName":"uploadfile","fileManagerListPath":"/ueditor/jsp/upload/file/","imageCompressBorder":1600,"imageManagerAllowFiles":[".png",".jpg",".jpeg",".gif",".bmp"],"imageManagerListPath":"/ueditor/jsp/upload/image/","fileMaxSize":51200000,"fileManagerAllowFiles":[".png",".jpg",".jpeg",".gif",".bmp",".flv",".swf",".mkv",".avi",".rm",".rmvb",".mpeg",".mpg",".ogg",".ogv",".mov",".wmv",".mp4",".webm",".mp3",".wav",".mid",".rar",".zip",".tar",".gz",".7z",".bz2",".cab",".iso",".doc",".docx",".xls",".xlsx",".ppt",".pptx",".pdf",".txt",".md",".xml"],"fileManagerActionName":"listfile","snapscreenInsertAlign":"none","scrawlActionName":"uploadscrawl","videoFieldName":"upfile","imageCompressEnable":true,"videoUrlPrefix":"","fileManagerUrlPrefix":"","catcherAllowFiles":[".png",".jpg",".jpeg",".gif",".bmp"],"imageManagerActionName":"listimage","snapscreenPathFormat":"/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}","scrawlPathFormat":"/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}","scrawlMaxSize":2048000,"imageInsertAlign":"none","catcherPathFormat":"/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}","catcherMaxSize":2048000,"snapscreenUrlPrefix":"","imagePathFormat":"/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}","imageManagerUrlPrefix":"","scrawlUrlPrefix":"","scrawlFieldName":"upfile","imageMaxSize":2048000,"imageAllowFiles":[".png",".jpg",".jpeg",".gif",".bmp"],"snapscreenActionName":"uploadimage","catcherActionName":"catchimage","fileFieldName":"upfile","fileUrlPrefix":"","imageManagerInsertAlign":"none","catcherLocalDomain":["127.0.0.1","localhost","img.baidu.com"],"filePathFormat":"/ueditor/jsp/upload/file/{yyyy}{mm}{dd}/{time}{rand:6}","videoPathFormat":"/ueditor/jsp/upload/video/{yyyy}{mm}{dd}/{time}{rand:6}","fileManagerListSize":20,"imageActionName":"uploadimage","imageFieldName":"upfile","imageUrlPrefix":"","scrawlInsertAlign":"none","fileAllowFiles":[".png",".jpg",".jpeg",".gif",".bmp",".flv",".swf",".mkv",".avi",".rm",".rmvb",".mpeg",".mpg",".ogg",".ogv",".mov",".wmv",".mp4",".webm",".mp3",".wav",".mid",".rar",".zip",".tar",".gz",".7z",".bz2",".cab",".iso",".doc",".docx",".xls",".xlsx",".ppt",".pptx",".pdf",".txt",".md",".xml"],"catcherUrlPrefix":"","imageManagerListSize":20,"catcherFieldName":"source","videoAllowFiles":[".flv",".swf",".mkv",".avi",".rm",".rmvb",".mpeg",".mpg",".ogg",".ogv",".mov",".wmv",".mp4",".webm",".mp3",".wav",".mid"]}
                     */
                    InputStream inStream = IndexController.class.getResourceAsStream(configPath);
                    StringBuilder builder = new StringBuilder();
                    try {
                        InputStreamReader reader = new InputStreamReader(inStream, "UTF-8");
                        BufferedReader bfReader = new BufferedReader(reader);
                        String tmpContent = null;
                        while ((tmpContent = bfReader.readLine()) != null) {
                            builder.append(tmpContent);
                        }
                        bfReader.close();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    // 过滤输入字符串, 剔除多行注释以及替换掉反斜杠
                    result = builder.toString().replaceAll("/\\*[\\s\\S]*?\\*/", "").replaceAll(" ", "");
                    System.out.println("result:" + result);

                } else if ("uploadimage".equals(value)) {// 上传文件
                    // 上传成功后返回的json数据
				/*
				 * {"state": "SUCCESS","original": "Hydrangeas.jpg","size": "595284","title":
				 * "1551927256870045443.jpg","type": ".jpg","url":
				 * "/upload/image/20190307/1551927256870045443.jpg"}
				 */

                    String originalFilename = upfile.getOriginalFilename();
                    String type = originalFilename.substring(originalFilename.indexOf("."), originalFilename.length());
                    long size = upfile.getSize();
                    System.out.println(originalFilename);
                    System.out.println(size);


                    String middlePath = DateFormatUtils.format(new Date(), "yyyyMMdd") + "/";

                    String fileFullName = rootPath + middlePath + originalFilename;
                    // 图片访问地址（tomcat服务器）
                    String url = "http://localhost:8080/img/" +middlePath+ originalFilename;
                    try {

                        File file = new File(fileFullName);
                        if(!file.getParentFile().exists()) {
                            file.getParentFile().mkdirs() ;
                        }

                        upfile.transferTo(file);

                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("state", "SUCCESS");
                        map.put("original", originalFilename);
                        map.put("size", size);
                        map.put("title", originalFilename);
                        map.put("type", type);
                        map.put("url", url);

                        result = JSON.toJSONString(map);
                        System.out.println("result : " + result);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                PrintWriter writer = response.getWriter();
                writer.write(result);
                writer.flush();
                writer.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
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
