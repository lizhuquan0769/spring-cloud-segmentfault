package com.segmentfault.spring.cloud.lesson12.user.service.client.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * Created by lizhuquan on 2018/9/3.
 */
public interface UserStream {
    String OUTPUT = "output";

    @Output(OUTPUT)
    MessageChannel output();
}
