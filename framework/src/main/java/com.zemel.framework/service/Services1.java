package com.zemel.framework.service;


import org.springframework.stereotype.Service;

@Service
public class Services1 extends ServiceInterface {

    @Override
    public String work() {
        return "Services1";
    }
}
