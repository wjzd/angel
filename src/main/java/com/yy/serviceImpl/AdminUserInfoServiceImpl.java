package com.yy.serviceImpl;

import com.yy.dao.AdminUserInfoMapper;
import com.yy.dao.UserInfoMapper;
import com.yy.pojo.AdminUserInfo;
import com.yy.service.AdminUserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("adminUserInfoService")
public class AdminUserInfoServiceImpl implements AdminUserInfoService {

    @Resource
    private AdminUserInfoMapper adminUserInfoMapper;
    @Override
    public List<AdminUserInfo> selectByAdmin(AdminUserInfo adminUserInfo) {
        return adminUserInfoMapper.selectByAdmin(adminUserInfo);
    }
}
