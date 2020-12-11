package com.zemel.web2.dto;

import com.zemel.web2.type.VideoType;
import com.zemel.web2.vo.VideoAnlizeVo;
import lombok.Data;

/**
 * @Author: zemel
 * @Date: 2020/10/12 21:04
 */
@Data
public class VideoAnlizeDto {
    private String baseUrl;
    private VideoType type;
    private String anlizeUrl;

    public VideoAnlizeVo toVo()
    {
        return new VideoAnlizeVo(this);
    }
    public VideoAnlizeDto(String url, VideoType type) {
        this.baseUrl = url;
        this.type = type;
    }

    public VideoAnlizeDto(String url) {
        this.baseUrl = url;
    }
}
