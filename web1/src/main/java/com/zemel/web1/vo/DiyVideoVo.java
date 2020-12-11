package com.zemel.web1.vo;

import com.zemel.framework.ComponentManager;
import com.zemel.framework.until.StringUtil;
import com.zemel.web1.ao.DiyVideoAo;
import com.zemel.web_framework.component.FileComponent;
import lombok.Data;

/**
 * @Author: zemel
 * @Date: 2020/7/27 22:19
 */
@Data
public class DiyVideoVo {
    private String src;
    private float x;
    private float y;
    private float width;
    private float height;
    private float bottom;
    private String alignmentY;
    public DiyVideoVo(DiyVideoAo diyVideoAo)
    {
        this.x = diyVideoAo.getX();
        this.y = diyVideoAo.getY();
        this.width = diyVideoAo.getWidth();
        this.height = diyVideoAo.getHeight();
        this.bottom = diyVideoAo.getBottom();
        this.alignmentY = diyVideoAo.getAlignmentY();
        if(!StringUtil.isNullOrEmpty(diyVideoAo.getSrc()))
            this.src = ComponentManager.getInstance().getComponent(FileComponent.class).getPicturePath()+diyVideoAo.getSrc();
    }
}
