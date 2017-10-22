package com.michong.controller;

import com.michong.entity.UserInfo;
import com.michong.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/user")
public class UseInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/index")
    public String index(){
        return "userinfo/index";
    }

    @RequestMapping(value = "/show")
    @ResponseBody
    public String show(@RequestParam(value = "name")String name){
        UserInfo userInfo = userInfoService.findUserByName(name);
        if(null != userInfo){
            return userInfo.getId()+"/"+userInfo.getName()+"/"+userInfo.getPassword();
        }else{
            return "null";
        }
    }
}
