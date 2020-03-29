package com.yy.service;


import com.yy.pojo.Collect;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("collectService")
public interface CollectService {
    public List<Collect> selectCollect(Collect collect);
}
