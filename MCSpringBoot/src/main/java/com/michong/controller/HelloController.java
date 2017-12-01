package com.michong.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
@Log4j
public class HelloController {

    @RequestMapping("/hello")
    @ResponseBody
    public String index() {
        return "Hello ,spring boot!";
    }

    public static void main(String[] args) throws Exception {
        log.info("this is just a test log info !");
        //运行之后在浏览器中访问：http://localhost:8080/hello
        SpringApplication.run(HelloController.class, args);
    }
}
