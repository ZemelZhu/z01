package com.zemel.framework.until;

import com.zemel.framework.vo.CityInfoVo;
import org.springframework.web.client.RestTemplate;
import sun.net.util.IPAddressUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: zemel
 * @Date: 2020/4/4 14:56
 */
public class ServletUtil {
    /**
     * 在HttpServletRequest获取到的 unknown IP
     */
    private static final String UNKNOWN = "unknown";

    private static final String cityRequest = "http://whois.pconline.com.cn/ipJson.jsp?json=true&ip=";

    /**
     * 获取客户端IP
     *
     * @param request
     * @return
     */
    public static String getRequestIP(HttpServletRequest request) {
        String ip = null;

        if (request == null) {
            return null;
        }

        ip = request.getHeader("x-real-ip");
        if (StringUtil.isNullOrEmpty(ip) == false
                && UNKNOWN.equalsIgnoreCase(ip) == false) {
            return ip;
        }

        ip = request.getHeader("x-forwarded-for");
        if (StringUtil.isNullOrEmpty(ip) == false
                && UNKNOWN.equalsIgnoreCase(ip) == false) {
            String[] ips = ip.split(",");
            if (ips.length > 1) {
                // 多级反向代理处理。取X-Forwarded-For中第一个非unknown的有效IP字符串。
                for (int i = 0; i < ips.length; i++) {
                    if (UNKNOWN.equalsIgnoreCase(ips[i]) == false) {
                        ip = ips[i];
                        break;
                    }
                }
            }

            return ip.trim();
        }

        ip = request.getHeader("Proxy-Client-IP");
        if (StringUtil.isNullOrEmpty(ip) == false
                && UNKNOWN.equalsIgnoreCase(ip) == false) {
            return ip;
        }

        ip = request.getHeader("WL-Proxy-Client-IP");
        if (StringUtil.isNullOrEmpty(ip) == false
                && UNKNOWN.equalsIgnoreCase(ip) == false) {
            return ip;
        }

        // 必须在最后啊，不然有代理的时候获取到的不是真实的客户端IP。
        ip = request.getRemoteAddr();
        if (StringUtil.isNullOrEmpty(ip) == false
                && UNKNOWN.equalsIgnoreCase(ip) == false) {
            return ip;
        }

        return null;
    }

    public static void main(String[] args) {
        String v4IP = getV4IP();
        System.out.println(v4IP);
    }

    public static String getV4IP() {
        String ip = "";
        String chinaz = "http://ip.chinaz.com";

        StringBuilder inputLine = new StringBuilder();
        String read = "";
        URL url = null;
        HttpURLConnection urlConnection = null;
        BufferedReader in = null;
        try {
            url = new URL(chinaz);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            while ((read = in.readLine()) != null) {
                inputLine.append(read + "\r\n");
            }
            //System.out.println(inputLine.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Pattern p = Pattern.compile("\\<dd class\\=\"fz24\">(.*?)\\<\\/dd>");
        Matcher m = p.matcher(inputLine.toString());
        if (m.find()) {
            String ipstr = m.group(1);
            ip = ipstr;
            //System.out.println(ipstr);
        }
        return ip;
    }

    public static boolean internalIp(String ip) {
        byte[] addr = IPAddressUtil.textToNumericFormatV4(ip);
        final byte b0 = addr[0];
        final byte b1 = addr[1];
        //10.x.x.x/8
        final byte SECTION_1 = 0x0A;
        //172.16.x.x/12
        final byte SECTION_2 = (byte) 0xAC;
        final byte SECTION_3 = (byte) 0x10;
        final byte SECTION_4 = (byte) 0x1F;
        //192.168.x.x/16
        final byte SECTION_5 = (byte) 0xC0;
        final byte SECTION_6 = (byte) 0xA8;
        switch (b0) {
            case SECTION_1:
                return true;
            case SECTION_2:
                if (b1 >= SECTION_3 && b1 <= SECTION_4) {
                    return true;
                }
            case SECTION_5:
                switch (b1) {
                    case SECTION_6:
                        return true;
                }
            default:
                return false;
        }
    }

    public static CityInfoVo getCityInfo(String ip) {
        if (internalIp(ip))
            ip = getV4IP();
        RestTemplate bean = SpringUtils.getBean(RestTemplate.class);
        String str = bean.getForObject(cityRequest + ip, String.class);
        CityInfoVo cityInfoVo = JsonUntil.stringToObject(str.trim(), CityInfoVo.class);
        return cityInfoVo;
    }

    public static boolean IsIntranet(HttpServletRequest request) {
        String requestIP = getRequestIP(request);
        if (requestIP.equals("127.0.0.1") || request.equals("localhost"))
            return true;
        return false;
    }
}
