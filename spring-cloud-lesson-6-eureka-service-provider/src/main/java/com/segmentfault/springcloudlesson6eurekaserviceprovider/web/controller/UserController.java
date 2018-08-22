package com.segmentfault.springcloudlesson6eurekaserviceprovider.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lizhuquan on 2018/8/22.
 */
@RestController
public class UserController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/users/{userId}")
    public Object user(@PathVariable Long userId) {
        Map<Object, Object> info = new HashMap<>();
        info.put("user", "User" + userId);
        info.put("server.port", serverPort);
        return info;
    }
}
