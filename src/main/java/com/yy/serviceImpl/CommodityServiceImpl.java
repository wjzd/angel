package com.yy.serviceImpl;

import com.yy.dao.AdminUserInfoMapper;
import com.yy.dao.CommodityMapper;
import com.yy.pojo.Commodity;
import com.yy.service.CommodityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("commodityService")
public class CommodityServiceImpl implements CommodityService {

    @Resource
    private CommodityMapper commodityMapper;
    @Override
    public List<Commodity> selectByCom(Commodity commodity) {
        return commodityMapper.selectByCom(commodity);
    }
}
