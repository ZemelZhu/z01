package com.zemel.tool.pbgenerate;

import com.zemel.framework.annotation.TerminalMessage;
import com.zemel.framework.until.ClassUtil;
import com.zemel.framework.until.FileUtil;
import com.zemel.tool.proto.CompileProtoBuid;
import com.zemel.tool.testBean.TestABean;
import com.zemel.tool.testBean.TestBBean;
import jdk.nashorn.internal.runtime.ParserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * @Author: zemel
 * @Date: 2020/12/12 17:52
 */
@Service
public class BuildProtoFileServiceImpl0 implements BuildProtoFileService {
    private Map<Long, ProtoInfo> messaageProtoInfoMap = new HashMap<>();

    private Map<String, Long> mesageOrderMap = new HashMap<>();
    private long protoMessageOrder = 0;
    @Autowired
    private CompileProtoBuid compileProtoBuid;
    @PostConstruct
    public void init()
    {
        ProtoInfo protoInfo = getProtoInfo(TestABean.class);
        ProtoInfo protoInfo1 = getProtoInfo(TestBBean.class);
        System.out.println(protoInfo);
        compileProtoBuid.start();
    }
    public static void main(String[] args) {
        BuildProtoFileServiceImpl0 buildProtoFileServiceImpl0 = new BuildProtoFileServiceImpl0();
        buildProtoFileServiceImpl0.init();
    }
    /**
     * 根据class获取唯一id
     */
    private long getOrder(Class<? extends TerminalMessage> message) {
        String messageName = message.getName();
        Long order = mesageOrderMap.get(messageName);
        if (order == null) {
            order = protoMessageOrder++;
            mesageOrderMap.put(messageName, order);
        }
        return order;
    }

    protected ProtoInfo getProtoInfo(Class<? extends TerminalMessage> message) {
        long order = getOrder(message);
        ProtoInfo protoInfo = messaageProtoInfoMap.get(order);
        if (protoInfo == null) {
            protoInfo = buildFile(message);
            messaageProtoInfoMap.put(order, protoInfo);
        }
        return protoInfo;
    }

    @Override
    public ProtoInfo buildFile(Class<? extends TerminalMessage> message) {

        List<Field> allFile = ClassUtil.getAllFile(message);
        int order = 1;
        StringBuilder head = new StringBuilder();
        head.append("\n");
        head.append("syntax = \"proto3\";\n" +
                "option java_package = \"com.zemel.data.proto.entiy\";\n");
        StringBuilder body = new StringBuilder();
        HashSet<String> strings = new HashSet<>();
        for (Field field : allFile) {
            try {
                field.setAccessible(true);
                Class<?> type = field.getType();
                    String name = field.getName();
                    String protoType = null;
                    String typeName = getTypeName(type,field,head,strings);
                    if(typeName==null)
                        continue;
                body.append("    ").append(typeName).append(" ").append(field.getName()).append("=").append(order).append(";\n");
                order++;
            }  catch (Exception e) {
                e.printStackTrace();
            throw new ParserException("转换异常"+field.getName());
        }
        }
        head.append("message ").append(message.getSimpleName()).append("PB{\n");
        StringBuilder builder = new StringBuilder();
        builder.append(head).append(body);
        builder.append("}\n");
        ProtoInfo protoInfo = new ProtoInfo(message);
        protoInfo.setProtoFile(builder.toString());
        FileUtil.writeFile(builder.toString(),"F:\\code\\Z01\\server\\z01\\data\\src\\main\\java\\com\\zemel\\data\\proto\\"+protoInfo.getFileName());
        return protoInfo;
    }



    private String getTypeName(Class<?> type,Field field, StringBuilder head,Set<String> importSets) throws NoSuchMethodException {
            if (ClassUtil.isInteger(type)) {
                return "int32";
            } else if (ClassUtil.isLong(type)) {
                return "int64";
            }else if (ClassUtil.isDouble(type)||ClassUtil.isFloat(type)) {
                return "float";
            }else if (ClassUtil.isBoolean(type)) {
                return "bool";
            }  else if (type.isAssignableFrom(String.class)) {
                return "string";
            } else if (type.isAssignableFrom(List.class) || type.isAssignableFrom(Set.class)) {
                Type genericSuperclass = field.getGenericType();
                if (genericSuperclass instanceof ParameterizedType) {
                    ParameterizedType pt = (ParameterizedType) genericSuperclass;
                    // 得到泛型里的class类型对象
                    Class<?> actualTypeArgument = (Class<?>)pt.getActualTypeArguments()[0];
                    return "repeated " + getTypeName(actualTypeArgument,field,head,importSets);
                }
            } else if (TerminalMessage.class.isAssignableFrom(type)) {
                String name = type.getSimpleName();
                if(!importSets.contains(name))
                {
                    head.append("import \"").append(name).append(".proto\";\n");
                    importSets.add(name);
                }
                return name+"PB";
            }
       return null;
    }
}
