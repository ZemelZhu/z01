package com.zemel.tool.testBean;

import com.zemel.framework.annotation.TerminalMessage;
import lombok.Data;

/**
 * @Author: zemel
 * @Date: 2020/12/12 21:22
 */
@Data
public class TestBBean implements TerminalMessage {
    private int id;
    private String name;
    public TestBBean()
    {
        this.id = 323;
        this.name = "sds";
    }
}
