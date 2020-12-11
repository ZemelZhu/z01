package com.zemel.framework.service;


import org.springframework.stereotype.Service;

@Service
public class Service2 extends ServiceInterface {
    @Override
    public String work() {
        return "Services2";
    }
}
