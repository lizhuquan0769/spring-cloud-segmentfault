package com.segmentfault.spring.cloud.lesson7.user.ribbon.client;

import com.netflix.loadbalancer.IRule;
import com.segmentfault.spring.cloud.lesson7.user.ribbon.client.rule.MyRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;

/**
 * Created by lizhuquan on 2018/8/23.
 */
@SpringBootApplication
public class UserRibbonClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserRibbonClientApplication.class, args);
    }

    @Bean
    public IRule myRule() {
        return new MyRule();
    }
}
