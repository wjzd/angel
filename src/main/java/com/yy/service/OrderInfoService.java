package com.yy.service;

import com.yy.pojo.OrderInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderInfoService")
public interface OrderInfoService {

    public List<OrderInfo> selectByOrderInfo(OrderInfo orderInfo);
}
