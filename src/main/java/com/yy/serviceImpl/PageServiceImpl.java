package com.yy.serviceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public List<Commodity> getListbyCategoryIdAndreecom(String  categoryName){
        CommodityExample commodityExample=new CommodityExample();
        CommodityExample.Criteria criteria=commodityExample.createCriteria();
        criteria.andCategoryEqualTo(categoryName);
        criteria.andIsoutEqualTo(1);
        criteria.andReecomEqualTo(2);
        List<Commodity> jinxuan=commodityMapper.selectByExample(commodityExample);
        return jinxuan;
    }

    @Override
    public List<Commodity> getListByTime(String  categoryName) {
        CommodityExample commodityExample=new CommodityExample();
        CommodityExample.Criteria criteria=commodityExample.createCriteria();
        if (!categoryName.equals("")){
            criteria.andCategoryEqualTo(categoryName);
        }
        criteria.andIsoutEqualTo(1);
        commodityExample.setOrderByClause("insertTime desc");
        return commodityMapper.selectByExample(commodityExample);
    }

    @Override
    public PageInfo<Commodity> getCommodityByuserId(int userId,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Commodity> commodityList=commodityMapper.getCommodityByuserId(userId);
        return new PageInfo(commodityList);
    }
}
