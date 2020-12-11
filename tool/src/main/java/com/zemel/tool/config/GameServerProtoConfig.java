package com.zemel.tool.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: zemel
 * @Date: 2020/1/16 23:05
 */
@Component
@Data
public class GameServerProtoConfig {
    @Value("${GameServerProtoConfig.inputPath}")
    private String inputPath;
    @Value("${GameServerProtoConfig.outPutPath}")
    private String outPutPath;
    @Value("${GameServerProtoConfig.execPath}")
    private String execPath;
    @Value("${GameServerProtoConfig.protoName}")
    private String protoName;
    @Value("${GameServerProtoConfig.build}")
    private boolean build;
    @Value("${GameServerProtoConfig.buildJAVA}")
    private boolean buildJAVA;
    @Value("${GameServerProtoConfig.buildJS}")
    private boolean buildJS;
    @Value("${GameServerProtoConfig.buildCS}")
    private boolean buildCS;
}
