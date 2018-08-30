package com.segmentfault.spring.cloud.lesson11.fallback;

import com.segmentfault.spring.cloud.lesson11.api.UserService;
import com.segmentfault.spring.cloud.lesson11.domain.User;

import java.util.Collections;
import java.util.List;

public class UserServiceFallback implements UserService {
    @Override
    public boolean saveUser(User user) {
        return false;
    }

    @Override
    public List<User> findAll() {
        return Collections.EMPTY_LIST;
    }
}
