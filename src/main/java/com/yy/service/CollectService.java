package com.yy.service;


import com.yy.pojo.Collect;
import com.yy.pojo.Commodity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("collectService")
public interface CollectService {
    public List<Collect> selectCollect(Collect collect);
    public int insertSelective(Collect collect);//新增
    public int deleteByPrimaryKey(Collect collect);//删除
}
