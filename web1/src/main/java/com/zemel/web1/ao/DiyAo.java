package com.zemel.web1.ao;

import com.zemel.framework.until.SerializeUtil;
import com.zemel.web1.dto.DiyDto;
import com.zemel.web1.entiy.model.PersistPlayerDiyInfo;
import lombok.Data;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zemel
 * @Date: 2020/7/18 13:49
 */
@Data
public class DiyAo {
    private int pageId;
    private int projectId;
    private String audioSrc;
    private String pageTitle;
    private String titleBGImageSrc;
    private String audioBGImageSrc;
    private String screenProportion;
    private String pageTitleColor;
    private List<HotspotAo> hotspotList;
    private boolean showTitle;
    public List<String> getResource()
    {
        List<String> list = new LinkedList<>();
        if(hotspotList!=null)
        {
            for(HotspotAo ao:hotspotList)
            {
                list.addAll(ao.getResource());
            }
        }
        return list;
    }
    public void check() {
    }

    public DiyDto toDto() {
        DiyDto diyDto = new DiyDto();
        diyDto.setHotspot(hotspotList);
        return diyDto;
    }

    public void addCommon(PersistPlayerDiyInfo playerDiyInfo) {
        playerDiyInfo.setTitle(pageTitle);
        playerDiyInfo.setScreenProportion(screenProportion);
        playerDiyInfo.setAudioBgImageSrc(audioBGImageSrc);
        playerDiyInfo.setTitleBgImageSrc(titleBGImageSrc);
        playerDiyInfo.setPageTitleColor(pageTitleColor);
        playerDiyInfo.setAudioSrc(audioSrc);
        playerDiyInfo.setShowTitle(showTitle);
        playerDiyInfo.setUpdateTime(new Date());
        byte[] serialize = SerializeUtil.serialize(toDto());
        playerDiyInfo.setDiyMessage(serialize);
    }
}
