package com.zemel.web1.vo;

import com.zemel.framework.ComponentManager;
import com.zemel.framework.until.StringUtil;
import com.zemel.web1.ao.DiyImageAo;
import com.zemel.web_framework.component.FileComponent;
import lombok.Data;

/**
 * @Author: zemel
 * @Date: 2020/7/27 22:18
 */
@Data
public class DiyImageVo {
    private String src;
    private float x;
    private float y;
    private float width;
    private float height;
    private float bottom;
    private String alignmentY;
    public DiyImageVo(DiyImageAo diyImageAo)
    {
        this.x = diyImageAo.getX();
        this.y = diyImageAo.getY();
        this.width = diyImageAo.getWidth();
        this.height = diyImageAo.getHeight();
        this.bottom = diyImageAo.getBottom();
        this.alignmentY = diyImageAo.getAlignmentY();
        if(!StringUtil.isNullOrEmpty(diyImageAo.getSrc()))
            this.src = ComponentManager.getInstance().getComponent(FileComponent.class).getPicturePath()+diyImageAo.getSrc();
    }
}
