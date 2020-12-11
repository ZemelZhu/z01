package com.zemel.mallserver.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author: zemel
 * @Date: 2020/7/9 21:16
 */
@Data
public class HomePictureVo {
    private int id;
    private List<String> pictureCode;
    private List<String>  url;
}
