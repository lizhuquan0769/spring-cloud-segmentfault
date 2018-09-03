package com.segmentfault.spring.cloud.lesson12.user.service.provider.stream;

import com.segmentfault.spring.cloud.lesson12.api.UserService;
import com.segmentfault.spring.cloud.lesson12.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * Created by lizhuquan on 2018/9/3.
 */
@Component
public class UserStreamListener {

    @Autowired
    @Qualifier("inMemoryUserService")
    private UserService userService;

    @StreamListener(UserStream.INPUT)
    public void receive(Message<User> user) {
        System.out.println(user.getPayload());
    }
}
