package com.yy.service;


import com.yy.pojo.AdminUserInfo;
import com.yy.pojo.CategoryMenu;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("categoryMennService")
public interface CategoryMennService {

    public List<CategoryMenu> selectAll(CategoryMenu categoryMenu);
}
