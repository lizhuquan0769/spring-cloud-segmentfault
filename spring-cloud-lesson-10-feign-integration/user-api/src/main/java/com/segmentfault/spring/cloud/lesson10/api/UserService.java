package com.segmentfault.spring.cloud.lesson10.api;

import com.segmentfault.spring.cloud.lesson10.domain.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * Created by lizhuquan on 2018/8/23.
 */
// 利用占位符避免未来整合时造成的硬编码
@FeignClient(name = "${user.service.name}")
public interface UserService{

    @PostMapping("/user/save")
    boolean saveUser(User user);

    @GetMapping("/user/find/all")
    List<User> findAll();
}
