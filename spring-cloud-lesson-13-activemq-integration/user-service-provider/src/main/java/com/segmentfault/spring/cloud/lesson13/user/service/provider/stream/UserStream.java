package com.segmentfault.spring.cloud.lesson13.user.service.provider.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by lizhuquan on 2018/9/3.
 */
public interface UserStream {

    String INPUT = "input";

    String ACTIVEMQ_INPUT = "activemq-in";

    @Input(INPUT)
    SubscribableChannel intput();

    @Input(ACTIVEMQ_INPUT)
    SubscribableChannel activemqInput();
}
