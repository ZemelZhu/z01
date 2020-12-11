package com.zemel.web_framework.component;

import com.zemel.framework.ComponentManager;
import com.zemel.framework.component.IComponent;
import com.zemel.framework.config.ServerConfig;
import com.zemel.framework.until.ServletUtil;
import com.zemel.framework.until.StringUtil;
import com.zemel.web_framework.config.FileConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zemel
 * @Date: 2020/7/18 11:03
 */

@Component
public class FileComponent implements IComponent {
    @Autowired
    private FileConfig fileConfig;
    public static final String DOWNLOAD = "/download";

    private String fileDownLoadUrl;

    private String contextPath;

    private String picturePath;

    private String hostAddress;
    private String host;
    @Autowired
    private ServerConfig serverConfig;
    @Autowired
    private Environment environment;

    public String getPicturePath() {
        if (picturePath == null) {
            synchronized (this) {
                if (picturePath == null) {
                    String resourcesPath = fileConfig.getResourcesPath();
                    if (StringUtil.isNullOrEmpty(resourcesPath))
                        picturePath = getContextPath() + fileConfig.getFileDownLoad();
                    else
                        picturePath = resourcesPath;
                }
            }
        }
        return picturePath;
    }

    public String getHost() {
        if (host == null) {
            try {
                if (!ComponentManager.getInstance().isDev()) {
                    if (serverConfig.getHost() != null)
                        host = serverConfig.getHost();
                    else
                        host = ServletUtil.getV4IP();
                } else
                    host = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
        return host;
    }

    public String getHttpHost() {
        return "http://" + getHost();

    }

    public String getHostAddress() {
        if (!StringUtil.isNullOrEmpty(hostAddress))
            return hostAddress;
        String port = environment.getProperty("server.port");
        hostAddress = "http://" + getHost() + ":" + port;
        return hostAddress;
    }

    public String getContextPath() {
        if (contextPath == null) {
            synchronized (this) {
                if (contextPath == null) {
                    String name = environment.getProperty("server.servlet.context-path");
                    contextPath = getHostAddress() + name;
                    LOGGER.error("picture:" + contextPath);
                }
            }
        }
        return contextPath;
    }

    public String getFileDownLoadUrl(HttpServletRequest request) {
        if (fileDownLoadUrl == null) {
            StringBuffer requestURL = request.getRequestURL();
            int index = requestURL.lastIndexOf("/");
            String url = requestURL.substring(0, index);
            fileDownLoadUrl = url + DOWNLOAD + "?fileName=";
        }
        return fileDownLoadUrl;
    }

    public String getFileDir() {
        return fileConfig.getFileDir();
    }


    @Override
    public boolean initialize() {
        return true;
    }

    @Override
    public boolean start() {
        return true;
    }

    @Override
    public void stop() {

    }

    @Override
    public boolean reload() {
        return true;
    }

    public List<String> getPictureUrl(String pic) {
        String[] split = pic.split(",");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            if (!split[i].equals("")) {
                String pictureUrl = split[i];
                pictureUrl = getPicturePath() + pictureUrl;
                list.add(pictureUrl);
            }

        }
        return list;
    }

    public List<String> getPicture(String pic) {
        String[] split = pic.split(",");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            if (!split[i].equals("")) {
                String pictureUrl = split[i];
                list.add(pictureUrl);
            }

        }
        return list;
    }
}