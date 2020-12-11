package com.zemel.mallserver.ao;

import com.zemel.data.type.DataStatus;
import com.zemel.mallserver.entiy.MallLabelInfo;
import lombok.Data;

import java.util.Date;

/**
 * @Author: zemel
 * @Date: 2020/4/5 11:50
 */
@Data
public class LabelAo {
    private int id;
    private String labelName;
    private String labelPicture;
    private int labelIndex;
    public MallLabelInfo getMallLabelInfo() {
        MallLabelInfo mallLabelInfo = new MallLabelInfo();
        mallLabelInfo.setLabelName(labelName);
        mallLabelInfo.setLabelPicture(labelPicture);
        mallLabelInfo.setLabelIndex(labelIndex);
        mallLabelInfo.setStatus(DataStatus.NORMAL.ordinal());
        mallLabelInfo.setCreateTime(new Date());
        mallLabelInfo.setUpdateTime(new Date());
        return mallLabelInfo;
    }
}
