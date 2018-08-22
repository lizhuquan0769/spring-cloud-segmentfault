package com.segmentfault.springcloudlesson6ribbon.web.controller;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by lizhuquan on 2018/8/22.
 */
@RestController
public class HomeController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public Object home() {
        return restTemplate.getForObject("http://spring-cloud-eureka-service-provider/users/1", String.class);
    }
}
