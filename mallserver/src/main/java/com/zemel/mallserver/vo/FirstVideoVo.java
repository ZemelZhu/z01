package com.zemel.mallserver.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author: zemel
 * @Date: 2020/5/1 16:49
 */
@Data
public class FirstVideoVo {
    private int id;
    private String videoCode;
    private String videoUrl;
    private List<String> videoUrls;
    private List<String> videoCodes;
}
