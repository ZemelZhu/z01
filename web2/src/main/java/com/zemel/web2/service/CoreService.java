package com.zemel.web2.service;

import com.zemel.web2.dto.VideoAnlizeDto;
import com.zemel.web2.logic.video.IVideoLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: zemel
 * @Date: 2020/10/12 21:10
 */
@Service
public class CoreService {
    @Autowired
    private List<IVideoLogic> videoLogicList;
    public VideoAnlizeDto anlize(String url)
    {
        if(videoLogicList!=null)
        {
            for(IVideoLogic logic:videoLogicList)
            {
                return logic.anlize(url);
            }
        }
        return new VideoAnlizeDto(url);
    }
}
