package com.segmentfault.spring.cloud.lesson13.user.service.client.web.controller;

import com.segmentfault.spring.cloud.lesson13.api.UserService;
import com.segmentfault.spring.cloud.lesson13.domain.User;
import com.segmentfault.spring.cloud.lesson13.user.service.client.stream.UserStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *注意：官方建议，客户端和客户端不要同时实现Feign接口，这里仅仅为了说明，实际情况最好使用组合的方式，而非继承
 */
@RestController("userServiceClientController")
public class UserServiceClientController implements UserService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserStream userStream;


    @PostMapping("/user/save/rabbitmq")
    public boolean saveUserRabbitMQ(@RequestBody User user) {
        return userStream.output().send(MessageBuilder.withPayload(user).build());
    }

    @PostMapping("/user/save/activemq")
    public boolean saveUserActiveMQ(@RequestBody User user) {
        return userStream.activemqOutput().send(MessageBuilder.withPayload(user).build());
    }

    @Override
    public boolean saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @Override
    public List<User> findAll() {
        return userService.findAll();
    }
}
