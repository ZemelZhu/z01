package com.zemel.tool.serialize;

import com.zemel.data.vo.PointVo;
import com.zemel.data.vo.TransformVo;
import com.zemel.framework.serialize.Protostuff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @Author: zemel
 * @Date: 2020/2/14 13:54
 */
public class SerializeTest {
    private static final Logger logger = LoggerFactory.getLogger(SerializeTest.class);
    public static void main(String[] args) {
        Protostuff protostuff = new Protostuff();
        PointVo position = PointVo.builder().x(1f).y(1f).z(2f).build();
        TransformVo vo = new TransformVo(position,position,position);
        byte[] serialize = protostuff.serialize(vo);
        logger.error(""+vo);
        logger.error(""+ Arrays.toString(serialize));
        vo = protostuff.deserialize(serialize,TransformVo.class);
        logger.error(""+vo);
    }
}
