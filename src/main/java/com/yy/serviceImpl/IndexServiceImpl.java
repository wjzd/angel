package com.yy.serviceImpl;

import com.yy.dao.CategoryMenuMapper;
import com.yy.pojo.CategoryMenu;
import com.yy.service.IndexService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IndexServiceImpl implements IndexService {

    @Resource
    private CategoryMenuMapper categoryMenuMapper;


    @Override
    public List<CategoryMenu> getMenuList() {
        return categoryMenuMapper.selectAll();
    }
}
