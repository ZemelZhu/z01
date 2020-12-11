package com.zemel.web2.logic.video;

import com.zemel.framework.until.JsonUntil;
import com.zemel.framework.until.StringUtil;
import com.zemel.web2.type.VideoType;
import com.zemel.web2.vo.DYResultVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: zemel
 * @Date: 2020/10/12 21:00
 */
@Service
public class DouYinVideo extends VideoAdapter {
    private A a = new A();
    private static class A{

        private void getrr(){

        }
    }
    @Override
    public VideoType getType() {
        return VideoType.DouYin;
    }
    public static String DOU_YIN_BASE_URL = "https://www.iesdouyin.com/web/api/v2/aweme/iteminfo/?item_ids=";

    public static String HUO_SHAN_BASE_URL = " https://share.huoshan.com/api/item/info?item_id=";

    public static String DOU_YIN_DOMAIN = "douyin";

    public static String HUO_SHAN_DOMAIN = "huoshan";
    public static String getLocation(String url) {
        try {
            URL serverUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) serverUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setInstanceFollowRedirects(false);
            conn.setRequestProperty("User-agent", "ua");//模拟手机连接
            conn.connect();
            String location = conn.getHeaderField("Location");
            return location;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    public static String matchNo(String redirectUrl) {
        List<String> results = new ArrayList<>();
        Pattern p = Pattern.compile("video/([\\w/\\.]*)/");
        Matcher m = p.matcher(redirectUrl);
        while (!m.hitEnd() && m.find()) {
            results.add(m.group(1));
        }
        return results.get(0);
    }
    public static String httpGet(String urlStr) throws Exception {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "text/json;charset=utf-8");
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        StringBuffer buf = new StringBuffer();
        String inputLine = in.readLine();
        while (inputLine != null) {
            buf.append(inputLine).append("\r\n");
            inputLine = in.readLine();
        }
        in.close();
        return buf.toString();
    }
    @Override
    public String doAnlize(String url) {
        a.getrr();
        try {
            String videoUrl = "";
            if (!StringUtils.isEmpty(url)) {
                String itemId = matchNo(url);
                logger.error(itemId);
                StringBuilder sb = new StringBuilder();
                sb.append(DOU_YIN_BASE_URL).append(itemId);
                logger.error(sb.toString());
                String videoResult = httpGet(sb.toString());

                if(!StringUtil.isNullOrEmpty(videoResult))
                {
                    DYResultVo dyResult = JsonUntil.stringToObject(videoResult, DYResultVo.class);
                    videoUrl = dyResult.getUrl();
                    logger.error(dyResult+"");
                }
                /**
                 * 4、无水印视频 url
                 */
                String videoRedirectUrl = getLocation(videoUrl);
                logger.error(videoRedirectUrl);
                return videoUrl;
            }
        } catch (Exception e) {
            logger.error("去水印异常 {}", e);
        }
        return "解析失败";
    }
}
