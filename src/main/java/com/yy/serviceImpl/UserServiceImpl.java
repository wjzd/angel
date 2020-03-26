package com.yy.serviceImpl;



import com.yy.dao.UserInfoMapper;

import com.yy.pojo.UserInfo;
import com.yy.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService{

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public List<UserInfo> selectByarb(UserInfo userInfo) {
        return userInfoMapper.selectByarb(userInfo);
    }

    @Override
    public void insertSelective(UserInfo userInfo) {
        userInfoMapper.insertSelective(userInfo);
    }
}
