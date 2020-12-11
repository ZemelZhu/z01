package com.zemel.framework.component;

import com.zemel.framework.annotation.SelfDrivenRequestMapping;
import com.zemel.framework.bean.ConversionMessage;
import com.zemel.framework.annotation.ISelfDrivenController;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ISelfDrivenControllerComponent implements IComponent {
    @Autowired
    protected List<ISelfDrivenController> controllers1;
    private Map<Integer, ISelfDrivenController> classMaps;
    private Map<Integer, Map<Integer, Method>> classMappings;


    @Override
    public boolean initialize() {
        classMaps = new HashMap<>();
        classMappings = new HashMap<>();
        return true;
    }

    @Override
    public boolean start() {
        for (ISelfDrivenController controller : controllers1) {
            Class<? extends ISelfDrivenController> selfControllerClass = controller.getClass();
            SelfDrivenRequestMapping classAnnotation = selfControllerClass.getAnnotation(SelfDrivenRequestMapping.class);
            if (classAnnotation != null) {
                classMaps.put(classAnnotation.value(), controller);
                Method[] methods = selfControllerClass.getDeclaredMethods();
                Map<Integer, Method> MethodMappings = classMappings.get(classAnnotation.value());
                if (MethodMappings == null) {
                    MethodMappings = new HashMap<>();
                    classMappings.put(classAnnotation.value(), MethodMappings);
                }
                for (Method method : methods) {
//                    method.setAccessible(true);
                    if (Modifier.isPublic(method.getModifiers())) {
                        SelfDrivenRequestMapping methodAnnotation = method.getAnnotation(SelfDrivenRequestMapping.class);
                        if (methodAnnotation != null) {
                            LOGGER.error("init method " + method.getName() + method.getModifiers());
                            MethodMappings.put(methodAnnotation.value(), method);
                        }
                    }

                }
            }
        }
        return true;
    }

    public Object invokeMethod(int controllerId, int methodId, Object... parms) {
        try {
            Map<Integer, Method> methodMappings = classMappings.get(controllerId);
            if (methodMappings != null) {
                Method method = methodMappings.get(methodId);
                if (method != null) {
                    LOGGER.error("invoke method controller:" + controllerId + "   methodId:" + methodId);
                    ISelfDrivenController iSelfDrivenController = classMaps.get(controllerId);
                    Object invoke = method.invoke(iSelfDrivenController, parms);
                    return invoke;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "not find method";
    }

    @Override
    public void stop() {

    }

    @Override
    public boolean reload() {
        return false;
    }

    public void invokeClass(ConversionMessage conversionMessage, Object holder) throws Exception {
        ISelfDrivenController iSelfDrivenController = classMaps.get(conversionMessage.getClassId());
        if (iSelfDrivenController != null) {

            iSelfDrivenController.processData(conversionMessage);
            /**
             * 是否反射注入，效率问题
             */
            if (iSelfDrivenController.invoke(holder)) {
                invokeMethod(conversionMessage.getClassId(), conversionMessage.getMethodId(), conversionMessage.getMessage(), holder);
            }
        }
    }
}
