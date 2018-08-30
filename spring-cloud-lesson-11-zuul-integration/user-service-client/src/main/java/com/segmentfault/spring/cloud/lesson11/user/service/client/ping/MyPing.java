package com.segmentfault.spring.cloud.lesson11.user.service.client.ping;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.Server;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * Created by lizhuquan on 2018/8/23.
 */
public class MyPing implements IPing {
    @Override
    public boolean isAlive(Server server) {
        // health检测地址
        // 通过spring组件来实现URL拼装
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
        builder.scheme("http");
        builder.host(server.getHost());
        builder.port(server.getPort());
        builder.path("/health");
        URI uri = builder.build().toUri();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> entity = restTemplate.getForEntity(uri, String.class);
        // 如果响应状态买等于OK，就返回true，否则false
        return HttpStatus.OK.equals(entity.getStatusCode());
    }
}
