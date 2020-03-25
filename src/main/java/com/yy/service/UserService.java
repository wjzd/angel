package com.yy.service;


import com.yy.pojo.SysUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public interface UserService {

    public List<SysUser> userList(SysUser sysUser);
}
