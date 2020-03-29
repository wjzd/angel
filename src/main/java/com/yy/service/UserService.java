package com.yy.service;


import com.yy.pojo.OrderInfo;
import com.yy.pojo.UserInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public interface UserService {

    public List<UserInfo> selectByarb(UserInfo userInfo);
    public void insertSelective(UserInfo userInfo);
    public int updateByPrimaryKeySelective(UserInfo userInfo);//修改
}
