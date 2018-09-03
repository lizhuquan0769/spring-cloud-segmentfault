package com.segmentfault.spring.cloud.lesson12.user.service.provider;

import com.segmentfault.spring.cloud.lesson12.user.service.provider.stream.UserStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * Created by lizhuquan on 2018/8/23.
 */
@SpringBootApplication
@EnableHystrix
@EnableDiscoveryClient
@EnableBinding(UserStream.class)
public class UserServiceProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceProviderApplication.class, args);
    }
}
