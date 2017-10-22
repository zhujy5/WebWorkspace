package com.michong.service;

import com.michong.entity.UserInfo;
import com.michong.repositoty.UserInfoRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepositoty userInfoRepositoty;

    @Override
    public UserInfo findUserByName(String name) {
        return null;
    }
}
