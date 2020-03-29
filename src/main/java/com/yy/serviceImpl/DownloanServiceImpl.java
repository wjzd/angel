package com.yy.serviceImpl;


import com.yy.dao.AdminUserInfoMapper;
import com.yy.dao.DownloanInfoMapper;
import com.yy.pojo.DownloanInfo;
import com.yy.service.DownloanService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("downloanService")
public class DownloanServiceImpl implements DownloanService {

    @Resource
    private DownloanInfoMapper downloanInfoMapper;
    @Override
    public List<DownloanInfo> selectByDown(DownloanInfo downloanInfo) {
        return downloanInfoMapper.selectByDown(downloanInfo);
    }
}
