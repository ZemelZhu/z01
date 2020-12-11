package com.zemel.mallserver.vo;

import com.zemel.mallserver.entiy.MallLabelInfo;
import lombok.Data;

/**
 * @Author: zemel
 * @Date: 2020/4/5 15:12
 */
@Data
public class MallLabelVo implements Comparable<MallLabelVo>{
    private int id;
    private String labelName;
    private String labelPicture;
    /** 是否首页独立显示 */
    private boolean isFirstLabel;
    private int labelIndex;
    public MallLabelVo(MallLabelInfo mallLabelInfo, String picturePath) {
        this.id = mallLabelInfo.getId();
        this.labelName = mallLabelInfo.getLabelName();
        this.labelPicture = picturePath + mallLabelInfo.getLabelPicture();
        this.isFirstLabel = mallLabelInfo.isSuccessInfo();
        this.labelIndex = mallLabelInfo.getLabelIndex();
    }

    @Override
    public int compareTo(MallLabelVo o) {
        return this.labelIndex-o.getLabelIndex();
    }
}
