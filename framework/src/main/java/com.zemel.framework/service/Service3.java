package com.zemel.framework.service;


import org.springframework.stereotype.Service;

@Service
public class Service3 extends ServiceInterface {
    @Override
    public String work() {
        return "Services3";
    }
}
