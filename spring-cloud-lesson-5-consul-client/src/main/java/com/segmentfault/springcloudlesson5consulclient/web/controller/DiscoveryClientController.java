package com.segmentfault.springcloudlesson5consulclient.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

/**
 * {@link DiscoveryClientController}
 * {@link RestController}
 *
 * @author lizhuquan
 * @since 1.0.0
 */
@RestController
public class DiscoveryClientController {

    private final DiscoveryClient discoveryClient;

    private final String currentApplicationName;

    @Autowired
    public DiscoveryClientController(DiscoveryClient discoveryClient, @Value("${spring.application.name}") String currentApplicationName) {
        this.discoveryClient = discoveryClient;
        this.currentApplicationName = currentApplicationName;
    }

    @GetMapping("/list/services")
    public List<String> listServices() {
        return discoveryClient.getServices();
    }

    @GetMapping("/list/service-instances")
    public List<ServiceInstance> listServiceInstances() {
        List<String> services = discoveryClient.getServices();
        List<ServiceInstance> serviceInstances = new LinkedList<>();
        services.forEach(s -> {
            serviceInstances.addAll(discoveryClient.getInstances(s));
        });
        return serviceInstances;
    }

    @GetMapping("/current/service-instance")
    public ServiceInstance getCurrentServiceInstance() {
        return discoveryClient.getLocalServiceInstance();
    }
}
