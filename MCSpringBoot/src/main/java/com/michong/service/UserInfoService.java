package com.michong.service;

import com.michong.entity.UserInfo;
import com.michong.repositoty.UserInfoRepositoty;
import org.springframework.beans.factory.annotation.Autowired;

public interface UserInfoService {

    UserInfo findUserByName(String name);

}
