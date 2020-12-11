package com.zemel.data.vo;

import com.zemel.data.proto.entiy.SceneMsg;
import lombok.Data;

/**
 * @Author: zemel
 * @Date: 2020/2/14 13:26
 */
@Data
public class TransformVo {
    public TransformVo(PointVo position, PointVo rotation, PointVo scale) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }

    private PointVo position;
    private PointVo rotation;
    private PointVo scale;
    public SceneMsg.TransformPB build()
    {
        SceneMsg.TransformPB.Builder builder = SceneMsg.TransformPB.newBuilder();
        return builder.setPosition(position.build()).setRotation(rotation.build()).setScale(scale.build()).build();
    }
}
