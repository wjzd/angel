package com.yy.serviceImpl;

import com.yy.dao.CategoryMenuMapper;
import com.yy.pojo.CategoryMenu;
import com.yy.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "indexService")
public class IndexServiceImpl implements IndexService {

    @Autowired
    private CategoryMenuMapper categoryMenuMapper;


    @Override
    public List<CategoryMenu> getMenuList() {
        return categoryMenuMapper.selectByExample(null);
    }
}
