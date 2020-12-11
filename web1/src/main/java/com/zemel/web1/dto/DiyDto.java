package com.zemel.web1.dto;

import com.zemel.framework.ComponentManager;
import com.zemel.framework.until.StringUtil;
import com.zemel.web1.ao.*;
import com.zemel.web1.entiy.PlayerDiyInfo;
import com.zemel.web1.vo.DiyVo;
import com.zemel.web1.vo.HotspotVo;
import com.zemel.web_framework.component.FileComponent;
import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: zemel
 * @Date: 2020/7/18 14:13
 */
@Data
public class DiyDto {
    private List<HotspotAo> hotspot;

    public DiyVo toVo(PlayerDiyInfo playerDiyInfo) {
        DiyVo diyVo = new DiyVo();
        diyVo.setPageId(playerDiyInfo.getId());
        diyVo.setPageTitle(playerDiyInfo.getTitle());
        diyVo.setScreenProportion(playerDiyInfo.getScreenProportion());
        diyVo.setPageTitleColor(playerDiyInfo.getPageTitleColor());
        diyVo.setShowTitle(playerDiyInfo.isShowTitle());
        diyVo.setTitleBGImageSrc(playerDiyInfo.getTitleBgImageSrc());
        diyVo.setAudioBGImageSrc(playerDiyInfo.getAudioBgImageSrc());
        diyVo.setProjectId(playerDiyInfo.getProjectId());
        if (hotspot != null)
            diyVo.setHotspotList(hotspot.stream().map(f -> new HotspotVo(f)).collect(Collectors.toList()));
        else
            diyVo.setHotspotList(Collections.emptyList());
        FileComponent component = ComponentManager.getInstance().getComponent(FileComponent.class);
        String qrCode = component.getPicturePath() + playerDiyInfo.getQrCode();
        String path = component.getHttpHost() + "/#/pageShow?id=" + playerDiyInfo.getId();
        diyVo.setPageUrl(path);
        diyVo.setPageQR(qrCode);
        if (!StringUtil.isNullOrEmpty(playerDiyInfo.getAudioSrc()))
            diyVo.setAudioSrc(component.getPicturePath() + playerDiyInfo.getAudioSrc());
        else
            diyVo.setAudioSrc("");
        if (!StringUtil.isNullOrEmpty(playerDiyInfo.getTitleBgImageSrc()))
            diyVo.setTitleBGImageSrc(component.getPicturePath() + playerDiyInfo.getTitleBgImageSrc());
        else
            diyVo.setTitleBGImageSrc("");
        return diyVo;
    }
}
