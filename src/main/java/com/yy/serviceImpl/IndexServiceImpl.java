package com.yy.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
        return commodityMapper.selectByExample(null);
    }

    @Override
    public PageInfo<Commodity> getCommodityList(String categoryName, Integer reecom, Integer pageNum, Integer pageSize) {
        Commodity commodity=new Commodity();
        if (!categoryName.equals("")){
            commodity.setCategory(categoryName);
        }
        commodity.setReecom(reecom);
        //按照时间排序
        String orderBy="insertTime desc";
        PageHelper.startPage(pageNum,pageSize,orderBy);
        List<Commodity> commodities=null;
        if (reecom!=0){
            commodities=commodityMapper.selectByCom(commodity);
        }else {
            if (!categoryName.equals("")){
                commodity.setCategory(categoryName);
            }else {
                commodity.setCategory(null);
            }
            commodity.setReecom(null);
            commodities=commodityMapper.selectByCom(commodity);
        }
        return new PageInfo(commodities);
    }

//    @Override
//    public List<Commodity> getCommodityList(String categoryName, Integer reecom) {
//        Commodity commodity=new Commodity();
//        commodity.setCategory(categoryName);
//        commodity.setReecom(reecom);
//        List<Commodity> commodities=null;
//        //不是推荐的商品，按照时间排序
//        if (reecom==0){
//            CommodityExample commodityExample=new CommodityExample();
//            CommodityExample.Criteria criteria=commodityExample.createCriteria();
//            if (!categoryName.equals("")){
//                criteria.andCategoryEqualTo(categoryName);
//            }
//            criteria.andIsoutEqualTo(1);
//            commodityExample.setOrderByClause("insertTime desc");
//            commodities=commodityMapper.selectByExample(commodityExample);
//        }else {
//            commodities=commodityMapper.selectByCom(commodity);
//        }
//        return commodities;
//    }
}
