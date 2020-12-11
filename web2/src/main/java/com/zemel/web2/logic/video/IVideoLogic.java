package com.zemel.web2.logic.video;

import com.zemel.web2.dto.VideoAnlizeDto;
import com.zemel.web2.type.VideoType;

/**
 * @Author: zemel
 * @Date: 2020/10/12 21:00
 */
public interface IVideoLogic {
    VideoType getType();
    public VideoAnlizeDto anlize(String url);
}
