package com.yy.service;

import com.github.pagehelper.PageInfo;
import com.yy.pojo.Collect;
import com.yy.pojo.Commodity;
import com.yy.pojo.UserInfo;

import java.util.List;

public interface PageService {
    //查询精选列表
    List<Commodity> getListbyCategoryIdAndreecom(String  categoryName);
    //查询列表 时间
    List<Commodity> getListByTime(String  categoryName);

    PageInfo<Commodity> getCommodityByuserId(int userId, Integer pageNum, Integer pageSize);

    int updateUserInfo(UserInfo userInfo);
}
