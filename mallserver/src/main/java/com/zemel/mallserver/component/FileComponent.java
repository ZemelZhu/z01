package com.zemel.mallserver.component;

import com.zemel.framework.ComponentManager;
import com.zemel.framework.component.IComponent;
import com.zemel.framework.config.ServerConfig;
import com.zemel.framework.until.ServletUtil;
import com.zemel.mallserver.config.FileConfig;
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
 * @Date: 2020/3/29 12:26
 */
@Component
public class FileComponent implements IComponent {
    @Autowired
    private FileConfig fileConfig;
    public static final String DOWNLOAD = "/download";

    private String fileDownLoadUrl;

    private String contextPath;

    private String picturePath;
    @Autowired
    private ServerConfig serverConfig;
    @Autowired
    private Environment environment;
    public String getPicturePath()
    {
        if(picturePath==null)
        {
            synchronized (this)
            {
                if(picturePath==null)
                {
                    picturePath = "http://file.strhjd.com:80/";
                    //picturePath=getContextPath()+fileConfig.getFileDownLoad();
                }
            }
        }
        return picturePath;
    }
    private String getHostAddress()  {
        String hostAddress="";
        try {
        if(serverConfig.getHost()!=null)
            hostAddress = serverConfig.getHost();
        else
        {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
            if(!ComponentManager.getInstance().isDev()) {
                hostAddress = ServletUtil.getV4IP();
            }
            String port = environment.getProperty("server.port");
            hostAddress = "http://"+hostAddress+":"+port;
        }
    } catch (UnknownHostException e) {
        e.printStackTrace();
    }
        return hostAddress;
    }

    public String getContextPath() {
        if (contextPath == null) {
            synchronized (this) {
                if (contextPath == null) {
                    String name= environment.getProperty("server.servlet.context-path");
                        contextPath = getHostAddress()+name;
                        LOGGER.error("picture:"+contextPath);
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
        if(fileConfig.getFileDir()==null)
            return "";
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
    public List<String> getPictureUrl(String pic)
    {
        String[] split = pic.split(",");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            if(!split[i].equals(""))
            {
                String pictureUrl = split[i];
                pictureUrl = getPicturePath()+pictureUrl;
                list.add(pictureUrl);
            }

        }
        return list;
    }
    public List<String> getPicture(String pic)
    {
        String[] split = pic.split(",");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            if(!split[i].equals(""))
            {
                String pictureUrl = split[i];
                list.add(pictureUrl);
            }

        }
        return list;
    }
}
