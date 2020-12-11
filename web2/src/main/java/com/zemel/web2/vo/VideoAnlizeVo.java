package com.zemel.web2.vo;

import com.zemel.web2.dto.VideoAnlizeDto;
import com.zemel.web2.type.VideoType;
import com.zemel.web_framework.vo.BaseVo;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @Author: zemel
 * @Date: 2020/10/12 22:14
 */
@Data
public class VideoAnlizeVo implements BaseVo {
    private String baseUrl;
    private VideoType type;
    private String anlizeUrl;
    public VideoAnlizeVo(VideoAnlizeDto videoAnlizeDto) {
        BeanUtils.copyProperties(videoAnlizeDto,this);
    }
}
