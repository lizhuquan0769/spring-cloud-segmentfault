package com.segmentfault.spring.cloud.spring.cloud.lesson8.api;

import com.segmentfault.spring.cloud.spring.cloud.lesson8.domain.User;

import java.util.List;

/**
 * Created by lizhuquan on 2018/8/23.
 */
public interface UserService{

    boolean saveUser(User user);

    List<User> findAll();
}
