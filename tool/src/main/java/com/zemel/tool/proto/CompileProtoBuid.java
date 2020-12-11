package com.zemel.tool.proto;

import com.zemel.framework.until.StringUtil;
import com.zemel.tool.config.CSProtoConfig;
import com.zemel.tool.config.GameServerProtoConfig;
import com.zemel.tool.config.JSProtoConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zemel
 * @Date: 2020/1/16 22:58
 */
@Service
public class CompileProtoBuid {
    private static final Logger LOGGER = LoggerFactory.getLogger(CompileProtoBuid.class);
    @Autowired
    private GameServerProtoConfig gameServerProtoConfig;
    @Autowired
    private JSProtoConfig jsProtoConfig;
    @Autowired
    private CSProtoConfig csProtoConfig;
    @PostConstruct
    private void start() {
        if (gameServerProtoConfig.isBuild()) {
            LOGGER.error("===========CompileProtoBuid  start==============");
            LOGGER.error(""+gameServerProtoConfig);
            build(gameServerProtoConfig.getInputPath(), gameServerProtoConfig.getOutPutPath(), gameServerProtoConfig.getExecPath());
            LOGGER.error("===========CompileProtoBuid  start==============");
        }
    }

    private void build(String inputPath, String outPutPath, String execPath) {
        Runtime rt = Runtime.getRuntime();
        List<String> fileList = getFileList(inputPath);
        String protoName = gameServerProtoConfig.getProtoName();
        for (String name: fileList) {
            if(!StringUtil.isNullOrEmpty(protoName))
            {
                if(!name.equals(protoName))
                    continue;
            }
            try {
                if(gameServerProtoConfig.isBuildJAVA())
                    buildJAVA(rt,name);
                if(gameServerProtoConfig.isBuildJS())
                {
                    buildJS(rt,name);
                }
                if(gameServerProtoConfig.isBuildCS())
                {
                    buildCS(rt,name);
                }
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("proto：" + name+"  build fail");
            }
        }
    }

    private void buildCS(Runtime rt, String name) throws IOException {
        //protoc.exe CommonMsg.proto --csharp_out=.
        String cmd = gameServerProtoConfig.getExecPath() + "/protoc.exe "+name+" --csharp_out="+csProtoConfig.getOutPutPath();
        rt.exec(cmd, null, new File(gameServerProtoConfig.getInputPath()));
        LOGGER.error("proto cs build success");
        LOGGER.error(cmd);
    }

    private void buildJS(Runtime rt, String name) throws IOException {
        //protoc.exe --js_out=import_style=commonjs,binary:. CommonMsg.proto
        String cmd = gameServerProtoConfig.getExecPath() + "/protoc.exe --js_out=import_style=commonjs,binary:"+jsProtoConfig.getOutPutPath()+" " + name;
        rt.exec(cmd, null, new File(gameServerProtoConfig.getInputPath()));
        LOGGER.error("proto js build success");
        LOGGER.error(cmd);
    }

    private void buildJAVA(Runtime rt, String name) throws IOException {
        //                Process p = rt.exec("start "+execPath+"\\protoc.exe "+f+" --java_out=./");
        //protoc.exe com/zemel/netty_proto/proto/CommonMsgPB.proto --java_out=./
        String cmd = gameServerProtoConfig.getExecPath() + "/protoc.exe " + name + " --java_out=" + gameServerProtoConfig.getOutPutPath();
//                Process exec = rt.exec(cmd);
        rt.exec(cmd, null, new File(gameServerProtoConfig.getInputPath()));
        LOGGER.error("proto java build success");
        LOGGER.error(cmd);
    }

    public static void main(String[] args) {
        String execPath = "F:\\code\\java\\idea\\spring\\src\\main\\resources";
        String inputPath = "F:\\code\\java\\eclipse\\FrameWork\\framework\\src\\main\\java\\com\\zemel\\framework\\socket\\proto";
        String outPutPath = "F:\\code\\java\\eclipse\\FrameWork\\framework\\src\\main\\java";
        Runtime rt = Runtime.getRuntime();
        List<String> fileList = getFileList(inputPath);
        fileList.forEach(f -> {
            System.out.println("proto：" + f);
            try {
//                Process p = rt.exec("start "+execPath+"\\protoc.exe "+f+" --java_out=./");
                String cmd = execPath + "/protoc.exe " + f + " --java_out=" + outPutPath;
//                Process exec = rt.exec(cmd);
                rt.exec(cmd, null, new File(inputPath));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /*
8      * 读取指定路径下的文件名和目录名
9      */
    public static List<String> getFileList(String path) {
        File file = new File(path);

        File[] fileList = file.listFiles();
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].isFile()) {
                String fileName = fileList[i].getName();
                if (fileName.endsWith(".proto")) {
                    String absolutePath = fileList[i].getAbsolutePath();
                    //LOGGER.error("path：" + absolutePath);
                    list.add(fileName);
                }
            }
        }
        return list;
    }
}
