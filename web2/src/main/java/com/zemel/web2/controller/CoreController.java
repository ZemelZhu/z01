package com.zemel.web2.controller;

import com.zemel.web2.dto.VideoAnlizeDto;
import com.zemel.web2.service.CoreService;
import com.zemel.web2.vo.VideoAnlizeVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zemel
 * @Date: 2020/10/12 20:41
 */
@RestController
@RequestMapping("core")
@Api(tags = "核心接口")
public class CoreController {
    @Autowired
    private CoreService coreService;
    @PostMapping("anlize")
    public VideoAnlizeVo anlize(String url)
    {

        VideoAnlizeDto anlize = coreService.anlize(url);
        return anlize.toVo();
    }
}
