package com.segmentfault.spring.cloud.lesson13.zuul.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created by lizhuquan on 2018/8/30.
 */
@SpringCloudApplication
@EnableZuulProxy
public class ZuulProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulProxyApplication.class, args);
    }
}
