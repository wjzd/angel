package com.yy.controller;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.yy.pojo.Collect;
import com.yy.pojo.ResultTable;
import com.yy.pojo.UserInfo;
import com.yy.service.CollectService;
import com.yy.service.CommodityService;
import com.yy.utils.MD5Util;
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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("/collectCon")
public class CollectCOntroller {

    @Resource
    private CollectService collectService;
    @RequestMapping("/toCollectList")
    public String toCollectList(){
        return "/views/collectList";
    }

    @RequestMapping("/collectList")
    @ResponseBody
    public String collectList(ResultTable resultTable, @RequestParam(value="pn",defaultValue="1") Integer pn, Collect collect){

        //获取第1页，5条内容，默认查询总数count
        /* 第一个参数是第几页；第二个参数是每页显示条数 */
        PageHelper.startPage(pn, 8);

        List<Collect> collectList=collectService.selectCollect(collect);
        resultTable.setCode(0);
        resultTable.setData(collectList);
        resultTable.setCount(collectList.size());
        return JSON.toJSONString(resultTable);
    }

    //用户点击收藏
    @RequestMapping("/updateCollect")
    @ResponseBody
    public void updateCollect(HttpServletResponse resp, HttpServletRequest request, Collect collect) throws JSONException {

        HttpSession session = request.getSession();

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
    }

}
