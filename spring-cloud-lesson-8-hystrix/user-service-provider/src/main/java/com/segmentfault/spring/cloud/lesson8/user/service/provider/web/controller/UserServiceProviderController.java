package com.segmentfault.spring.cloud.lesson8.user.service.provider.web.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.segmentfault.spring.cloud.spring.cloud.lesson8.api.UserService;
import com.segmentfault.spring.cloud.spring.cloud.lesson8.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by lizhuquan on 2018/8/23.
 */
@RestController
public class UserServiceProviderController {

    private final static Random random = new Random();

    @Autowired
    private UserService userService;

    @PostMapping("/user/save")
    public boolean userSave(@RequestBody User user) {
        return userService.saveUser(user);
    }

    /**
     * 增加超时处理
     * @return
     */
    @HystrixCommand(
            commandProperties = {
                // 设置超时时间100毫秒
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100")
            },
            // 设置fallback方法
            fallbackMethod = "fallbackForGetUsers"
    )
    @GetMapping("/user/list")
    public List<User> getUsers() throws InterruptedException {
        long executeTime = random.nextInt(200);
        System.out.println("Execute Time:" + executeTime);
        if (executeTime > 100L) {
            Thread.sleep(executeTime);
        }

        return userService.findAll();
    }

    public List<User> fallbackForGetUsers() {
        return Collections.EMPTY_LIST;
    }
}

