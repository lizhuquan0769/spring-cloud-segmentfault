package com.segmentfault.spring.cloud.lesson11.user.service.client;

import com.netflix.loadbalancer.IRule;
import com.segmentfault.spring.cloud.lesson11.api.UserService;
import com.segmentfault.spring.cloud.lesson11.user.service.client.rule.MyRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Created by lizhuquan on 2018/8/23.
 */
@SpringBootApplication
@EnableCircuitBreaker // 客户端用spring cloud的方式实现hystrix
@EnableFeignClients(clients = UserService.class) // 声明UserService接口作为Feign的调用
@EnableDiscoveryClient
public class UserServiceClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceClientApplication.class, args);
    }

    @Bean
    public IRule myRule() {
        return new MyRule();
    }

    /**
     * {@link org.springframework.cloud.netflix.ribbon.PropertiesFactory} 包含所有组件的配置映射
     */
    // 可以通过配置方式进行装配，如：user-service-provider.service.NFLoadBalancerPingClassName=MyPing
//    @Bean
//    public IPing myPing() {
//        return new MyPing();
//    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
