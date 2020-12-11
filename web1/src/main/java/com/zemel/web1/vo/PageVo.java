package com.zemel.web1.vo;

import com.zemel.web_framework.vo.BaseVo;
import lombok.Data;

import java.util.List;

/**
 * @Author: zemel
 * @Date: 2020/8/11 19:05
 */
@Data
public class PageVo implements BaseVo {
    private int page;
    private int total;
    private List<?> data;

    public PageVo(int page, List<?> data, int projectCount) {
        this.page = page;
        this.data = data;
        this.total = projectCount;
    }
}
