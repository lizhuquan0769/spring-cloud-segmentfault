package com.segmentfault.spring.cloud.lesson13.user.service.client.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.segmentfault.spring.cloud.lesson13.domain.User;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

/**
 * User Ribbon Client HytrixCommand
 * Created by lizhuquan on 2018/8/24.
 */
public class UserRibbonClientHystrixCommand extends HystrixCommand<List<User>> {

    private final String providerServiceName;
    private final RestTemplate restTemplate;

    public UserRibbonClientHystrixCommand(String providerServiceName, RestTemplate restTemplate) {
        super(HystrixCommandGroupKey.Factory.asKey(providerServiceName), 100);
        this.providerServiceName =  providerServiceName;
        this.restTemplate = restTemplate;
    }

    /**
     * 主逻辑实现
     * @return
     * @throws Exception
     */
    @Override
    protected List<User> run() throws Exception {
        return restTemplate.getForObject("http://" + providerServiceName + "/user/list", List.class);
    }

    /**
     * fallback实现
     * @return
     */
    @Override
    protected List<User> getFallback() {
        return Collections.EMPTY_LIST;
    }
}
