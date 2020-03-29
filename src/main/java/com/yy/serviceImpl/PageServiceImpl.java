package com.yy.serviceImpl;
import com.yy.dao.CommodityMapper;
import com.yy.pojo.Commodity;
import com.yy.pojo.CommodityExample;
import com.yy.service.PageService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class PageServiceImpl implements PageService {

    @Resource
    private CommodityMapper commodityMapper;

    @Override
    public List<Commodity> getListbyCategoryIdAndreecom(int categoryId){
        CommodityExample commodityExample=new CommodityExample();
        CommodityExample.Criteria criteria=commodityExample.createCriteria();
        criteria.andCategoryEqualTo(String.valueOf(categoryId));
        criteria.andIsoutEqualTo(1);
        criteria.andReecomEqualTo(2);
        List<Commodity> jinxuan=commodityMapper.selectByExample(commodityExample);
        return jinxuan;
    }

    @Override
    public List<Commodity> getListByTime() {
        CommodityExample commodityExample=new CommodityExample();
        CommodityExample.Criteria criteria=commodityExample.createCriteria();
        commodityExample.setOrderByClause("insertTime desc");
        return commodityMapper.selectByExample(commodityExample);
    }
}
