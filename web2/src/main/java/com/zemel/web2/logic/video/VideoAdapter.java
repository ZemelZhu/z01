package com.zemel.web2.logic.video;

import com.zemel.web2.dto.VideoAnlizeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: zemel
 * @Date: 2020/10/12 21:01
 */
public abstract class VideoAdapter implements IVideoLogic {
    protected static final Logger logger = LoggerFactory.getLogger(VideoAdapter.class);
    public VideoAnlizeDto anlize(String url)
    {
        VideoAnlizeDto videoAnlizeDto = new VideoAnlizeDto(url, getType());
        String s = doAnlize(url);
        videoAnlizeDto.setAnlizeUrl(s);
        return videoAnlizeDto;
    }
    public abstract String doAnlize(String url);
}
