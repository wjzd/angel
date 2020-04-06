package com.yy.controller;


import com.alibaba.fastjson.JSONObject;
import com.sun.imageio.plugins.common.ImageUtil;
import lombok.Value;
import org.apache.logging.log4j.util.ProcessIdUtil;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/ueditor")
public class UeditorController {

    @RequestMapping("/config")
    @ResponseBody
    public String uploadConfig() {
        String s = "{\n" +
                "            \"imageActionName\": \"uploadimage\",\n" +
                "                \"imageFieldName\": \"upfile\", \n" +
                "                \"imageMaxSize\": 2048000, \n" +
                "                \"imageAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"], \n" +
                "                \"imageCompressEnable\": true, \n" +
                "                \"imageCompressBorder\": 1600, \n" +
                "                \"imageInsertAlign\": \"none\", \n" +
                "                \"imageUrlPrefix\": \"\",\n" +
                "                \"imagePathFormat\": \"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\" }";
        return s;
    }

    @PostMapping("/upload")
    public Map<String,String> uploadFile(MultipartFile upfile){

        System.out.println("999999");
        // 项目在容器中实际发布运行的根路径
        String realPath = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        String fileUrl="";
        realPath=realPath+ "static/ueditorImg/";
        System.out.println(realPath);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHMMSS");
        String dateString=simpleDateFormat.format(new Date());
        String fileName="";
        if (!upfile.isEmpty()) {
            Map<String, String> resObj = new HashMap<>();
            String name=upfile.getOriginalFilename();//文件上传的真实名称
            String suffixName=name.substring(name.lastIndexOf("."));
            String hash= Integer.toHexString(new Random().nextInt());
            fileName=dateString+hash+suffixName;
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

        } else {
            return null;
        }
        System.out.println(fileUrl);
        Map<String,String> map=new HashMap<>();
        map.put("static","SUCCESS");
        map.put("url",fileUrl);
        map.put("title",fileName);
        map.put("original",fileName);
        return map;
    }


}
