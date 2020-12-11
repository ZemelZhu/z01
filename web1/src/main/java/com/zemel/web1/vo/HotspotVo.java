package com.zemel.web1.vo;

import com.zemel.web1.ao.HotspotAo;
import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: zemel
 * @Date: 2020/7/27 22:16
 */
@Data
public class HotspotVo {
    private String hotspotTitle;
    private List<DiyTextVo> textList;
    private List<DiyImageVo> imageList;
    private List<DiyVideoVo> videoList;
    public HotspotVo(HotspotAo hotspotAo)
    {
        this.hotspotTitle = hotspotAo.getHotspotTitle();
        if(hotspotAo.getTextList()!=null)
            this.textList = hotspotAo.getTextList().stream().map(f->new DiyTextVo(f)).collect(Collectors.toList());
        else
            this.textList = Collections.emptyList();
        if(hotspotAo.getImageList()!=null)
            this.imageList = hotspotAo.getImageList().stream().map(f->new DiyImageVo(f)).collect(Collectors.toList());
        else
            this.imageList = Collections.emptyList();
        if(hotspotAo.getVideoList()!=null)
            this.videoList = hotspotAo.getVideoList().stream().map(f->new DiyVideoVo(f)).collect(Collectors.toList());
        else
            this.videoList = Collections.emptyList();

    }
}
