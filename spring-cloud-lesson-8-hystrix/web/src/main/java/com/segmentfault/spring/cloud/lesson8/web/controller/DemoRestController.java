package com.segmentfault.spring.cloud.lesson8.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeoutException;

/**
 * Created by lizhuquan on 2018/8/24.
 */
@RestController
public class DemoRestController {

    private final static Random random = new Random();
    /**
     * 当方法执行时间超过100ms时，触发异常
     * @return
     */
    @GetMapping("")
    public String index() throws Exception {
        long executeTime = random.nextInt(200);

        if (executeTime > 100) {
            throw new TimeoutException("Execution is timeout!");
        }

        return "Hello World";
    }
}
