package com.segmentfault.springcloudlesson6eurekaserviceprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudLesson6EurekaServiceProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudLesson6EurekaServiceProviderApplication.class, args);
	}
}
