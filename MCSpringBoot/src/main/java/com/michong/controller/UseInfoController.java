package com.michong.controller;

import com.michong.entity.UserInfo;
import com.michong.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UseInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/index")
    public String index(){
        return "userinfo/index";
    }

    @GetMapping(value = "/userInfoList")
    @ResponseBody
    public List<UserInfo> show(@RequestParam(value = "name")String name){
        List<UserInfo> userInfo = userInfoService.findUserByName(name);
        if(null != userInfo){
            return userInfo;
        }else{
            return null;
        }
    }
}
