package com.yy.controller;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String config(HttpServletRequest request, HttpServletResponse response) {
        String json = "";
        response.setContentType("application/json");
        // 获取项目在磁盘的绝对路径
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        try {
            // 将josn文件读到Stirng
            json = IOUtils.toString(new FileInputStream(new File(path + "/static/ueditor/jsp/config.json")), Charsets.UTF_8.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }


    @PostMapping("/upload")
    @ResponseBody
    public Map<String,String> uploadFile(MultipartFile upfile,HttpServletRequest request, HttpServletResponse response) throws IOException {

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
