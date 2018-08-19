package com.segmentfault.springcloudlesson4erurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringCloudLesson4ErurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudLesson4ErurekaServerApplication.class, args);
	}
}
