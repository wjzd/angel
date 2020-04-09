package com.yy.service;

import com.yy.pojo.Commodity;

import java.util.List;

public interface PageService {
    //查询精选列表
    List<Commodity> getListbyCategoryIdAndreecom(String  categoryName);
    //查询列表 时间
    List<Commodity> getListByTime(String  categoryName,Integer pn);
}
