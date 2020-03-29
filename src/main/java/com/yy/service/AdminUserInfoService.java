package com.yy.service;

import com.yy.pojo.AdminUserInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("adminUserInfoService")
public interface AdminUserInfoService {
    public List<AdminUserInfo> selectByAdmin(AdminUserInfo adminUserInfo);
}
