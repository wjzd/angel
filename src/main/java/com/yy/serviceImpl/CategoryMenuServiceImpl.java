package com.yy.serviceImpl;

import com.yy.dao.AdminUserInfoMapper;
import com.yy.dao.CategoryMenuMapper;
import com.yy.pojo.CategoryMenu;
import com.yy.service.CategoryMennService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("categoryMennService")
public class CategoryMenuServiceImpl implements CategoryMennService{

    @Resource
    private CategoryMenuMapper categoryMenuMapper;
    @Override
    public List<CategoryMenu> selectAll(CategoryMenu categoryMenu) {
        return categoryMenuMapper.selectAll();
    }
}
