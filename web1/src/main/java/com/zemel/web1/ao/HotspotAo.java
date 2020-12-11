package com.zemel.web1.ao;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: zemel
 * @Date: 2020/7/20 21:59
 */
@Data
public class HotspotAo {
    private String hotspotTitle;
    private String audioSrc;
    private List<DiyTextAo> textList;
    private List<DiyImageAo> imageList;
    private List<DiyVideoAo> videoList;
    public List<String> getResource()
    {
        List<String> list = new LinkedList<>();
        if(imageList!=null)
        {
            list.addAll(imageList.stream().map(f->f.getSrc()).collect(Collectors.toList()));
        }
        if(videoList!=null)
        {
            list.addAll(videoList.stream().map(f->f.getSrc()).collect(Collectors.toList()));
        }
        return list;
    }
}
