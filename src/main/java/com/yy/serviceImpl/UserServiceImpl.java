package com.yy.serviceImpl;


import com.yy.dao.SysUserMapper;
import com.yy.pojo.SysUser;
import com.yy.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService{

    @Resource
    private SysUserMapper sysUserMapper;
    @Override
    public List<SysUser> userList(SysUser sysUser) {
        return sysUserMapper.userList(sysUser);
    }
}
