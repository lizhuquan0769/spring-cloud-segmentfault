package com.segmentfault.spring.cloud.lesson10.user.service.client.web.controller;

import com.segmentfault.spring.cloud.lesson10.api.UserService;
import com.segmentfault.spring.cloud.lesson10.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *注意：官方建议，客户端和客户端不要同时实现Feign接口，这里仅仅为了说明，实际情况最好使用组合的方式，而非继承
 */
@RestController
public class UserServiceClientController implements UserService {

    @Autowired
    private UserService userService;

    @Override
    public boolean saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @Override
    public List<User> findAll() {
        return userService.findAll();
    }
}
