package com.segmentfault.spring.cloud.lesson13.zuul.proxy.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lizhuquan on 2018/8/30.
 */
@RestController
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
