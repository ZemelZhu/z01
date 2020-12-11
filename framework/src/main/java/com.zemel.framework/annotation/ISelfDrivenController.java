package com.zemel.framework.annotation;

import com.zemel.framework.bean.ConversionMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface ISelfDrivenController {
    static final Logger LOGGER = LoggerFactory.getLogger(ISelfDrivenController.class);

    void processData(ConversionMessage conversionMessage) throws Exception;

    boolean invoke(Object holder);
}
