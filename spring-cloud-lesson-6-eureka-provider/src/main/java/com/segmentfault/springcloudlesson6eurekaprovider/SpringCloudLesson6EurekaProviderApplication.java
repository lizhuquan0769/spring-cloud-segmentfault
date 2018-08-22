package com.segmentfault.springcloudlesson6eurekaprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudLesson6EurekaProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudLesson6EurekaProviderApplication.class, args);
	}
}
