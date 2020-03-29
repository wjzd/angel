package com.yy.service;

import com.yy.pojo.Commodity;
import com.yy.pojo.DownloanInfo;
import com.yy.pojo.OrderInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderInfoService")
public interface OrderInfoService {

    public List<OrderInfo> selectByOrderInfo(OrderInfo orderInfo);
    public int insertSelective(OrderInfo orderInfo);//新增
    public int updateByPrimaryKeySelective(OrderInfo orderInfo);//修改
}
