package com.yy.service;

import com.yy.pojo.CategoryMenu;
import com.yy.pojo.Commodity;

import java.util.List;

public interface IndexService {
    List<CategoryMenu> getMenuList();

    List<Commodity> getCommodityList(Integer pn);
}
