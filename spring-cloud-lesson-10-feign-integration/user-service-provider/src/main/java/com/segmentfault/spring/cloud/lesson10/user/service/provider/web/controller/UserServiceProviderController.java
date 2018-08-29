package com.segmentfault.spring.cloud.lesson10.user.service.provider.web.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.segmentfault.spring.cloud.lesson10.api.UserService;
import com.segmentfault.spring.cloud.lesson10.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by lizhuquan on 2018/8/23.
 */
@RestController
public class UserServiceProviderController implements UserService {

    private final static Random random = new Random();

    @Autowired
    @Qualifier("inMemoryUserService")
    private UserService userService;

    // method的注解会继承，但是方法参数的注解不能被继承，所以这里需要用@RequestBody注解
    @Override
    public boolean saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @Override
    public List<User> findAll() {
        return userService.findAll();
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

