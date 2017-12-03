package com.michong.service;

import com.michong.entity.UserInfo;
import com.michong.repositoty.UserInfoRepositoty;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface UserInfoService {

    List<UserInfo> findUserByName(String name);

}
