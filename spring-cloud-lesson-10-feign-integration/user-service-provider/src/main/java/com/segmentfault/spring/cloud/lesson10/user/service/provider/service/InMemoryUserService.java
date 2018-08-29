package com.segmentfault.spring.cloud.lesson10.user.service.provider.service;

import com.segmentfault.spring.cloud.lesson10.api.UserService;
import com.segmentfault.spring.cloud.lesson10.domain.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by lizhuquan on 2018/8/23.
 */
@Service("inMemoryUserService")
public class InMemoryUserService implements UserService {

    private Map<Long, User> repository = new ConcurrentHashMap<>();

    @Override
    public boolean saveUser(User user) {
        return repository.put(user.getId(), user) == null;
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(repository.values());
    }
}
