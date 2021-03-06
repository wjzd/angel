package com.yy.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yy.pojo.Collect;
import com.yy.pojo.DownloanInfo;
import com.yy.pojo.ResultTable;
import com.yy.service.CommodityService;
import com.yy.service.DownloanService;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;

import javax.annotation.Resource;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/downloanCon")
public class DownloanController {


    @Resource
    private DownloanService downloanService;
    @RequestMapping("/toDownList")
    public String toDownList(){
        return "/views/downList";
    }

    @RequestMapping(value="/downList",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String downList(ResultTable resultTable, @RequestParam("limit")int limit, @RequestParam("page")int page, DownloanInfo downloanInfo){

        String orderBy="downTime desc";
        PageHelper.startPage(page,limit,orderBy);
        List<DownloanInfo> downList=downloanService.selectByDown(downloanInfo);
        PageInfo<Collect> pageInfo=new PageInfo(downList);
        resultTable.setCode(0);
        resultTable.setData(pageInfo.getList());
        resultTable.setCount((int)pageInfo.getTotal());
        return JSON.toJSONString(resultTable);
    }


    @RequestMapping(value = "uploadFile")
    @ResponseBody
    public String uploadImage(@RequestParam("file") MultipartFile file) {

        System.out.println("文件上传");
        // 项目在容器中实际发布运行的根路径
        String realPath = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        String fileUrl="";
        realPath=realPath+ "static/upload/";
        System.out.println(realPath);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHMMSS");
        String dateString=simpleDateFormat.format(new Date());
        if (!file.isEmpty()) {
            Map<String, String> resObj = new HashMap<>();
            String name=file.getOriginalFilename();//文件上传的真实名称
            String suffixName=name.substring(name.lastIndexOf("."));
            String hash= Integer.toHexString(new Random().nextInt());
            String fileName=dateString+hash+suffixName;
            fileUrl="/static/upload/"+fileName;
            try {
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File(realPath, fileName)));
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (IOException e) {
                resObj.put("msg", "error");
                resObj.put("code", "1");
                e.printStackTrace();
                return JSONObject.toJSONString(resObj);
            }
            resObj.put("msg", "ok");
            resObj.put("code", "0");
            resObj.put("fileUrl", fileUrl);
            return JSONObject.toJSONString(resObj);
        } else {
            return null;
        }
    }


}
