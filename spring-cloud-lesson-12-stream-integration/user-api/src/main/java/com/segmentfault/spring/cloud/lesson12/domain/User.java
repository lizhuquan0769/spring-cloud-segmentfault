package com.segmentfault.spring.cloud.lesson12.domain;

import java.io.Serializable;

/**
 * Created by lizhuquan on 2018/8/23.
 */
public class User implements Serializable {

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
