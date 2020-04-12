package com.yy.controller;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.yy.pojo.CategoryMenu;
import com.yy.pojo.Commodity;
import com.yy.pojo.OrderInfo;
import com.yy.pojo.ResultTable;
import com.yy.service.CommodityService;
import com.yy.service.OrderInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/orderCon")
public class OrderInfoController {

    @Resource
    private OrderInfoService orderInfoService;
    @RequestMapping("/toOrderList")
    public String toOrderList(){
        return "/views/orderList";
    }

    @RequestMapping("/getOrderList")
    @ResponseBody
    public String getOrderList(ResultTable resultTable, @RequestParam(value="pn",defaultValue="1") Integer pn, OrderInfo orderInfo){
        //获取第1页，5条内容，默认查询总数count
        /* 第一个参数是第几页；第二个参数是每页显示条数 */
        PageHelper.startPage(pn, 8);

        List<OrderInfo> orderInfoList=orderInfoService.selectByOrderInfo(orderInfo);
        System.out.println("订单："+orderInfoList.get(0).getCommodity().getComname());
        resultTable.setCode(0);
        resultTable.setData(orderInfoList);
        resultTable.setCount(orderInfoList.size());
        return JSON.toJSONString(resultTable);
    }

    @RequestMapping("/orderInfoById")
    public String orderInfoById(Model model, OrderInfo orderInfo){

        if(orderInfo.getId()!=null){
            orderInfo=orderInfoService.selectByOrderInfo(orderInfo).get(0);
        }
        model.addAttribute("orderInfo",orderInfo);

        return "/views/orderInfo";
    }
}
