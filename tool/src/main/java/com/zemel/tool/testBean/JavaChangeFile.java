package com.zemel.tool.testBean;

import com.google.protobuf.GeneratedMessageV3;
import com.zemel.framework.annotation.TerminalMessage;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: zemel
 * @Date: 2020/12/12 22:54
 */
public class JavaChangeFile {

    public static void main(String[] args) {
        TestABean testABean = new TestABean();
        toProto(testABean);
    }
    private static Map<Class,Class> map = new ConcurrentHashMap<>();

    private static Class<?> getProtoClass(Class<?> clazz)
    {
        Class clazz1 = com.zemel.data.proto.entiy.TestBBean.TestBBeanPB.class;
        String classPath = "com.zemel.data.proto.entiy."+clazz.getSimpleName()+"$"+clazz.getSimpleName()+"PB";
        try {
            Class<?> aClass = Class.forName(classPath);
            map.put(aClass,clazz);
            return aClass;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static Class<?> getBeanClass(Class<?> clazz)
    {
        return map.get(clazz);
    }
    public static void toProto(TestABean testABean)
    {
        com.zemel.data.proto.entiy.TestABean.TestABeanPB.Builder builder = com.zemel.data.proto.entiy.TestABean.TestABeanPB.newBuilder();
        ArrayList<String> strings = new ArrayList<>();
        builder.addAllNames(strings);
        builder.addNames("4");
        builder.setName("43");
        testABean.getNames();
        com.zemel.data.proto.entiy.TestABean.TestABeanPB testABeanPB = toProtoBuffer(testABean, com.zemel.data.proto.entiy.TestABean.TestABeanPB.class);
        System.out.println(testABeanPB.toBuilder().toString());
        TestABean testABean1 = fromProtoBuffer(testABeanPB, TestABean.class);
        testABeanPB.getNamesList();
        System.out.println(testABean1);

    }
    /**
     * ProtoBuffer object to POJO
     */
    private static <T> T fromProtoBuffer(GeneratedMessageV3 pbObject, Class<T> modelClass) {
        T model = null;
        try {
            Class<? extends GeneratedMessageV3> aClass = pbObject.getClass();
            model = modelClass.newInstance();
            while(true)
            {
                Field[] modelFields = modelClass.getDeclaredFields();
                if (modelFields != null && modelFields.length > 0) {
                    for (Field modelField : modelFields) {
                        String fieldName = modelField.getName();
                        String name = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                        Class<?> fieldType = modelField.getType();
                        try {
                            Object value;
                            if(TerminalMessage.class.isAssignableFrom(fieldType))
                            {
                                value = aClass.getMethod("get" + name);
                                value  =toProtoBuffer(value,getBeanClass(fieldType));
                            }
                            else if(List.class.isAssignableFrom(fieldType)||Set.class.isAssignableFrom(fieldType))
                            {

                                value = aClass.getMethod("get" + name+"List").invoke(pbObject);
                                List<?> objects = (List<?>) value;
                                Collection objects1 = null;
                                if(List.class.isAssignableFrom(fieldType))
                                    objects1 = new ArrayList<>();
                                else
                                    objects1 = new HashSet<>();
                                for(Object obj:objects)
                                {
                                    Object objj = obj;
                                    if(TerminalMessage.class.isAssignableFrom(obj.getClass()))
                                    {
                                        objj = toProtoBuffer(value,getBeanClass(fieldType));
                                    }
                                    objects1.add(objj);
                                }
                                value = objects1;
                            }
                            else
                            {
                                value = aClass.getMethod("get" + name).invoke(pbObject);
                            }


                            Method modelSetMethod = modelClass.getMethod("set" + name, fieldType);
                            modelSetMethod.invoke(model, value);
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        }
                    }
                }


                modelClass = (Class<T>) modelClass.getSuperclass();
                if(modelClass==null)
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return model;
    }
    /**
     * POJO -> ProtoBuffer object
     */
    private static <T> T toProtoBuffer(Object model, Class<T> pbClass) {
        if (!GeneratedMessageV3.class.isAssignableFrom(pbClass)) {
            throw new RuntimeException("Not ProtoBuffer message type");
        }
        T pbObject = null;
        try {
            Object pbBuilder = pbClass.getDeclaredMethod("newBuilder").invoke(null);

                Field[] pbFields = pbClass.getDeclaredFields();
                if (pbFields != null && pbFields.length > 0) {
                    for (Field pbField : pbFields) {
                        pbField.setAccessible(true);
                        String fieldName = pbField.getName().substring(0, pbField.getName().length() - 1);
                        String name = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                        Class<?> fieldType = pbField.getType();
                        try {
                            Method modelGetMethod = model.getClass().getMethod("get" + name);
                            Object value = modelGetMethod.invoke(model);
                            if (value != null) {
                                Method pbBuilderSetMethod = null;
                                if (TerminalMessage.class.isAssignableFrom(value.getClass())) {
                                    value = toProtoBuffer(value, getProtoClass(model.getClass()));
                                } else if (List.class.isAssignableFrom(fieldType) || Set.class.isAssignableFrom(fieldType)) {
                                    Collection<?> objects = (Collection<?>) value;
                                    ArrayList<Object> objects1 = new ArrayList<>();
                                    for (Object obj : objects) {
                                        Object objj = obj;
                                        if (TerminalMessage.class.isAssignableFrom(obj.getClass())) {
                                            objj = toProtoBuffer(obj, getProtoClass(obj.getClass()));
                                        }
                                        objects1.add(objj);
                                    }
                                    value = objects1;
                                    pbBuilderSetMethod = pbBuilder.getClass().getMethod("addAll" + name, Iterable.class);
                                } else {
                                    pbBuilderSetMethod = pbBuilder.getClass().getMethod("set" + name, fieldType);
                                }
                                pbBuilderSetMethod.invoke(pbBuilder, value);
                            }
                        } catch (NoSuchMethodException e) {
                        }
                    }
                }
            pbObject = (T) pbBuilder.getClass().getDeclaredMethod("build").invoke(pbBuilder);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pbObject;
    }
}
