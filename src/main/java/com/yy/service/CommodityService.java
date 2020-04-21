package com.yy.service;

import com.github.pagehelper.PageInfo;
import com.yy.pojo.Commodity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("commodityService")
public interface CommodityService {
    public List<Commodity> selectByCom(Commodity commodity);//查询商品

    public PageInfo<Commodity> selectCom(Commodity commodity,int pageNum,int pageSize);
    public int insertSelective(Commodity commodity);//新增
    public int updateByPrimaryKeySelective(Commodity commodity);//修改
}
