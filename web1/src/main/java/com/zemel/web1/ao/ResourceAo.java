package com.zemel.web1.ao;

import com.zemel.framework.exception.TipException;
import com.zemel.framework.until.StringUtil;
import com.zemel.web1.type.ResourceType;
import lombok.Data;

/**
 * @Author: zemel
 * @Date: 2020/7/28 21:45
 */
@Data
public class ResourceAo {
    private String resource;
    private int type;
    private String name;
    private int projectId;
    public void check() {
        if (StringUtil.isNullOrEmpty(resource, name))
            throw new TipException("资源或名称不能为空");
        if(ResourceType.parse(type)==null)
            throw new TipException("找不到資源類型");
    }
}
