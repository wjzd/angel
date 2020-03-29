package com.yy.serviceImpl;

import com.yy.dao.CategoryMenuMapper;
import com.yy.dao.CommodityMapper;
import com.yy.pojo.CategoryMenu;
import com.yy.pojo.Commodity;
import com.yy.pojo.CommodityExample;
import com.yy.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "indexService")
public class IndexServiceImpl implements IndexService {

    @Autowired
    private CategoryMenuMapper categoryMenuMapper;
    @Autowired
    private CommodityMapper commodityMapper;

    @Override
    public List<CategoryMenu> getMenuList() {
        return categoryMenuMapper.selectByExample(null);
    }

    @Override
    public List<Commodity> getCommodityList() {
        CommodityExample commodityExample=new CommodityExample();
        CommodityExample.Criteria criteria=commodityExample.createCriteria();
        criteria.andIsoutEqualTo(1);
        criteria.andReecomEqualTo(1);
        List<Commodity> jinxuan=commodityMapper.selectByExample(commodityExample);
        return commodityMapper.selectByExample(null);
    }
}
