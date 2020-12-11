package com.zemel.web1.vo;

import com.zemel.web1.ao.DiyTextAo;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @Author: zemel
 * @Date: 2020/7/27 22:17
 */
@Data
public class DiyTextVo {
    private String content;
    private String textAlignment;
    private String textFamily;
    private String textColor;
    private String textFontSize;
    private float x;
    private float y;
    private float width;
    private float height;
    private float bottom;
    private String alignmentY;
    private String textWeight;
    private String textItalic;
    private String textUnderline;
    public DiyTextVo(DiyTextAo diyTextAo)
    {
//        this.content = diyTextAo.getContent();
//        this.textAlignment = diyTextAo.getTextAlignment();
//        this.textFamily = diyTextAo.getTextFamily();
//        this.textColor = diyTextAo.getTextColor();
//        this.textFontSize = diyTextAo.getTextFontSize();
//        this.x = diyTextAo.getX();
//        this.y = diyTextAo.getY();
//        this.width = diyTextAo.getWidth();
//        this.height = diyTextAo.getHeight();
//        this.bottom = diyTextAo.getBottom();
//        this.alignmentY = diyTextAo.getAlignmentY();
//        this.textWeight = diyTextAo.getTextWeight();
//        this.textItalic = diyTextAo.getTextItalic();
//        this.textUnderline =diyTextAo.getTextUnderline();
        BeanUtils.copyProperties(diyTextAo,this);
    }
}
