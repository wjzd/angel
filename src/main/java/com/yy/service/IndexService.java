package com.yy.service;

import com.github.pagehelper.PageInfo;
import com.yy.pojo.CategoryMenu;
import com.yy.pojo.Commodity;

import java.util.List;

public interface IndexService {
    List<CategoryMenu> getMenuList();

    List<Commodity> getCommodityList();


    PageInfo<Commodity> getCommodityList(String categoryName, Integer reecom, Integer pageNum, Integer pageSize);
}
