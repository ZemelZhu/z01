package com.zemel.data.vo;

import com.zemel.data.proto.entiy.SceneMsg;
import lombok.Builder;
import lombok.Data;

/**
 * @Author: zemel
 * @Date: 2020/2/14 13:26
 */
@Data
@Builder
public class PointVo {
    private float x;
    private float y;
    private float z;
    private float w;
    public SceneMsg.PostionPB build()
    {
        SceneMsg.PostionPB.Builder builder = SceneMsg.PostionPB.newBuilder();
        return builder.setX(x).setY(y).setZ(z).setW(w).build();
    }
}
