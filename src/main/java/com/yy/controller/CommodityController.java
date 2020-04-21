package com.yy.controller;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yy.pojo.*;
import com.yy.service.CategoryMennService;
import com.yy.service.CommodityService;
import com.yy.service.DownloanService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/views")
public class CommodityController {

    @Resource
    private CommodityService commodityService;
    @Resource
    private CategoryMennService categoryMennService;
    @Resource
    private DownloanService downloanService;

    @RequestMapping("/toCommodity")
    public String toCommodity(){
        return "/views/commodity";
    }

    @RequestMapping(value = "/getCommodityList",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getList(ResultTable resultTable,Commodity commodity,@RequestParam("limit")int limit, @RequestParam("page")int page){
        PageInfo<Commodity> pageInfo=commodityService.selectCom(null,page,limit);
        resultTable.setCode(0);
        resultTable.setData(pageInfo.getList());
        resultTable.setCount((int)pageInfo.getTotal());
        return JSON.toJSONString(resultTable);
    }
    @RequestMapping("/addCommodityInit")
    public String addCommodityInit(Model model,Commodity commodity){
        //商品分類
        List<CategoryMenu> cateList=categoryMennService.selectAll(null);
        if(commodity.getId()!=null){
            commodity=commodityService.selectByCom(commodity).get(0);
        }
        model.addAttribute("cateList",cateList);
        model.addAttribute("commodity",commodity);
        return "/views/addCommodity";
    }
    @RequestMapping("/addCommodity")
    public String addCommodity(Model model,Commodity commodity){
        try {
            System.out.println("文本："+commodity.getDetails());
            if(commodity.getId()!=null){//修改

                int count=commodityService.updateByPrimaryKeySelective(commodity);
                System.out.println("修改："+count);
            }else{
                commodityService.insertSelective(commodity);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("文本："+commodity.getDetails());

        return "redirect:/views/toCommodity";
    }
    @RequestMapping("/updateCommodityInit")
    public String updateCommodityInit(Model model,Commodity commodity){

        //商品分類
        List<CategoryMenu> cateList=categoryMennService.selectAll(null);
        if(commodity.getId()!=null){
            commodity=commodityService.selectByCom(commodity).get(0);
        }

        model.addAttribute("cateList",cateList);
        model.addAttribute("commodity",commodity);

        return "/views/addCommodity";
    }

    @RequestMapping("/commodityView")
    public String commodityView(Model model,Commodity commodity){
        if(commodity.getId()!=null){
            commodity=commodityService.selectByCom(commodity).get(0);
        }

        model.addAttribute("commodity",commodity);
        return "/views/commodityView";
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


    @RequestMapping("/comDetailsInit")
    public String comDetailsInit(Model model,Commodity commodity){

        if(commodity.getId()!=null){
            commodity=commodityService.selectByCom(commodity).get(0);
        }

        model.addAttribute("commodity",commodity);
        return "/views/commodityView";
    }

}
