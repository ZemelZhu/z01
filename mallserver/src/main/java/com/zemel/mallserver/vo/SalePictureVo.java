package com.zemel.mallserver.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author: zemel
 * @Date: 2020/5/5 11:58
 */
@Data
public class SalePictureVo {
    private int id;
    private List<String> pictureCode;
    private List<String>  url;
}
