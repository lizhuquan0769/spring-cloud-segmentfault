package com.segmentfault.spring.cloud.lesson12.user.service.provider.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by lizhuquan on 2018/9/3.
 */
public interface UserStream {

    String INPUT = "input";

    @Input(INPUT)
    SubscribableChannel intput();
}
