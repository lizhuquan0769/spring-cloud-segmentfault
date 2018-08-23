package com.segmentfault.spring.cloud.lesson7.api;

import com.segmentfault.spring.cloud.lesson7.domain.User;

import java.util.List;

/**
 * Created by lizhuquan on 2018/8/23.
 */
public interface UserService{

    boolean saveUser(User user);

    List<User> findAll();
}
