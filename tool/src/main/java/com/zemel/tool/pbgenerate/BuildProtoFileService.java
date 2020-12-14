package com.zemel.tool.pbgenerate;

/**
 * @Author: zemel
 * @Date: 2020/12/12 17:51
 */

import com.zemel.framework.annotation.TerminalMessage;

/**
 * 根据代码生成proto文件
 * */
public interface BuildProtoFileService {
    /**
     * 根据message对象返回生成的proto描述
     * */
    public ProtoInfo buildFile(Class<? extends TerminalMessage> message);
}
