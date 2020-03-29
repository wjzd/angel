package com.yy.serviceImpl;


import com.yy.dao.AdminUserInfoMapper;
import com.yy.dao.CollectMapper;
import com.yy.pojo.Collect;
import com.yy.service.CollectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("collectService")
public class CollectServiceImpl implements CollectService {

    @Resource
    private CollectMapper collectMapper;
    @Override
    public List<Collect> selectCollect(Collect collect) {
        return collectMapper.selectCollect(collect);
    }

    @Override
    public int insertSelective(Collect collect) {
        return collectMapper.insertSelective(collect);
    }

    @Override
    public int deleteByPrimaryKey(Collect collect) {
        return collectMapper.deleteByPrimaryKey(collect.getId());
    }
}
