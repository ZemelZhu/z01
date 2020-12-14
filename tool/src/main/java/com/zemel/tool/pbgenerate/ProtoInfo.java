package com.zemel.tool.pbgenerate;

import com.zemel.framework.annotation.TerminalMessage;
import lombok.Data;

/**
 * @Author: zemel
 * @Date: 2020/12/12 17:56
 */
@Data
public class ProtoInfo {
    private Class<? extends TerminalMessage> message;
    private String protoFile;
    private String fileName;
    private String javaChangeFile;
    public ProtoInfo(Class<? extends TerminalMessage> message) {
        this.message = message;
        this.fileName = message.getSimpleName()+".proto";
    }
}
