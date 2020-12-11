package com.zemel.web2.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * @Author: zemel
 * @Date: 2020/10/12 21:18
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DYResultVo {
    private List<ItemListBean> item_list;

    public DYResultVo() {
    }
    public String getUrl()
    {
        return item_list.get(0).getVideo().getPlay_addr().getUrl_list().get(0);
    }
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ItemListBean {
        private Video video;

        public ItemListBean() {
        }
    }
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class Video {
        private PlayVideo play_addr;

        public Video() {
        }
    }
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class PlayVideo{
        private List<String> url_list;

        public PlayVideo() {
        }
    }
}
