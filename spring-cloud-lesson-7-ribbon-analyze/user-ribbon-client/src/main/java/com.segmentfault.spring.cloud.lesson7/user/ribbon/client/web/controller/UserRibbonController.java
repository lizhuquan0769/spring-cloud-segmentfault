package com.segmentfault.spring.cloud.lesson7.user.ribbon.client.web.controller;

import com.segmentfault.spring.cloud.lesson7.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * Created by lizhuquan on 2018/8/23.
 */
@RestController
public class UserRibbonController {

    private String userProviderName = "user-service-provider";
    /**
     * 负载均衡器客户端
     */
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("")
    public String index() throws IOException {
        User user = new User();
        user.setId(1L);
        user.setName("小马哥");

        // 选择指定的service id
        ServiceInstance serviceInstance = loadBalancerClient.choose(userProviderName);
        return loadBalancerClient.execute(userProviderName, serviceInstance, (instance -> {
            //  服务器实例，获取主机IP 和 主机端口
            String host = instance.getHost();
            int port = instance.getPort();
            String url = "http://" + host + ":" + port + "/user/save";

            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.postForObject(url, user, String.class);
        }));
    }
}
