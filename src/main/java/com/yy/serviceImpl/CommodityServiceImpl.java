package com.yy.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    @Override
    public PageInfo<Commodity> selectCom(Commodity commodity,int pageNum,int pageSize) {
        String orderBy="insertTime desc";
        PageHelper.startPage(pageNum,pageSize,orderBy);
        List<Commodity> commodities=commodityMapper.selectByCom(commodity);
        return new PageInfo(commodities);
    }

    @Override
    public int insertSelective(Commodity commodity) {
        return commodityMapper.insertSelective(commodity);
    }

    @Override
    public int updateByPrimaryKeySelective(Commodity commodity) {
        return commodityMapper.updateByPrimaryKeySelective(commodity);
    }
}
