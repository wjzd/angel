package com.yy.serviceImpl;

import com.yy.dao.AdminUserInfoMapper;
import com.yy.dao.OrderInfoMapper;
import com.yy.pojo.OrderInfo;
import com.yy.service.OrderInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("orderInfoService")
public class OrderInfoServiceImpl implements OrderInfoService {

    @Resource
    private OrderInfoMapper orderInfoMapper;
    @Override
    public List<OrderInfo> selectByOrderInfo(OrderInfo orderInfo) {
        return orderInfoMapper.selectByOrderInfo(orderInfo);
    }

    @Override
    public int insertSelective(OrderInfo orderInfo) {
        return orderInfoMapper.insertSelective(orderInfo);
    }

    @Override
    public int updateByPrimaryKeySelective(OrderInfo orderInfo) {
        return orderInfoMapper.updateByPrimaryKeySelective(orderInfo);
    }
}
