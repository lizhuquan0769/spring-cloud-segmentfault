package com.segmentfault.spring.cloud.lesson7.user.service.provider.web.controller;

import com.segmentfault.spring.cloud.lesson7.api.UserService;
import com.segmentfault.spring.cloud.lesson7.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lizhuquan on 2018/8/23.
 */
@RestController
public class UserServiceProviderController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/save")
    public boolean userSave(@RequestBody User user) {
        return userService.saveUser(user);
    }
}

