package com.zemel.web1.vo;

import com.zemel.web_framework.vo.BaseVo;
import lombok.Data;

import java.util.List;

/**
 * @Author: zemel
 * @Date: 2020/7/18 14:15
 */
@Data
public class DiyVo implements BaseVo {
    private int pageId;
    private String pageUrl;
    private String pageQR;
    private String audioSrc;
    private String screenProportion;
    private int projectId;
    private String pageTitle;
    private String titleBGImageSrc;
    private String audioBGImageSrc;
    private String pageTitleColor;
    private boolean showTitle;
    private List<HotspotVo> hotspotList;

}
