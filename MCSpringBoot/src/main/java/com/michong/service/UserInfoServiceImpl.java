package com.michong.service;

import com.michong.entity.UserInfo;
import com.michong.repositoty.UserInfoRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepositoty userInfoRepositoty;

    @Override
    public List<UserInfo> findUserByName(String name) {
        return userInfoRepositoty.findUserByName(name);
    }
}
