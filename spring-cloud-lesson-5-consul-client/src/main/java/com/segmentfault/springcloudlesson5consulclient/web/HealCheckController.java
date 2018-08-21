package com.segmentfault.springcloudlesson5consulclient.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealCheckController {

    @GetMapping("/check")
    public String check() {
        return "OK";
    }
}
