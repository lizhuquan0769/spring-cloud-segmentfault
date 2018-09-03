package com.segmentfault.spring.cloud.lesson12.user.service.client.web.controller;

import com.segmentfault.spring.cloud.lesson12.user.service.client.hystrix.UserRibbonClientHystrixCommand;
import com.segmentfault.spring.cloud.lesson12.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

/**
 * Created by lizhuquan on 2018/8/23.
 */
@RestController
public class UserRibbonController {

    private String providerServiceName = "user-service-provider";
    @Autowired
    private RestTemplate restTemplate;

    // 负载均衡器客户端
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @GetMapping("")
    public String index() throws IOException {
        User user = new User();
        user.setId(1L);
        user.setName("小马哥");

        // 选择指定的service id
        ServiceInstance serviceInstance = loadBalancerClient.choose(providerServiceName);
        return loadBalancerClient.execute(providerServiceName, serviceInstance, (instance -> {
            //  服务器实例，获取主机IP 和 主机端口
            String host = instance.getHost();
            int port = instance.getPort();
            String url = "http://" + host + ":" + port + "/user/save";

            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.postForObject(url, user, String.class);
        }));
    }


    @GetMapping("/user-service-provider/user/list")
    public List<User> getUserList() {
        // spring 配置方式
//       return restTemplate.getForObject("http://" + providerServiceName + "/user/list", List.class);

        // spring cloud 配置方式
        return new UserRibbonClientHystrixCommand(providerServiceName, restTemplate).execute();
    }

}
