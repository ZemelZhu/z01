package com.zemel.web1.type;

/**
 * @Author: zemel
 * @Date: 2020/7/28 21:51
 */
public enum ResourceType {
    /**0图片*/
    Picture,
    /**1视频*/
    Video,
    /**2音频*/
    Audio,
    /**3标题背景图片*/
    TitlePicture,
    /**4音频背景图片*/
    AudioPicture,
    ;
    public static ResourceType parse(int type)
    {
        for(ResourceType t:ResourceType.values())
        {
            if(t.ordinal()==type)
                return t;
        }
        return null;
    }
}
