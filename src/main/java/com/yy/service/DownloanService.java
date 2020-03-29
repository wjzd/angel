package com.yy.service;


import com.yy.pojo.Commodity;
import com.yy.pojo.DownloanInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("downloanService")
public interface DownloanService {

    public List<DownloanInfo> selectByDown(DownloanInfo downloanInfo);
    public int insertSelective(DownloanInfo downloanInfo);//新增
}
