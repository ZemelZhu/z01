package com.zemel.web_framework.controller;

import com.zemel.framework.ComponentManager;
import com.zemel.framework.login.RolesLoginService;
import com.zemel.framework.vo.ResponseVo;
import com.zemel.web_framework.component.FileComponent;
import com.zemel.web_framework.component.ResourceComponent;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @Author: zemel
 * @Date: 2020/7/18 12:27
 */
@RestController
@Api(tags = "文件上传相关")
public class FileUploadController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);
    @Autowired
    private FileComponent fileComponent;
    @Autowired
    private RolesLoginService rolesLoginService;
    @Autowired
    private ResourceComponent resourceComponent;
    @PostMapping({"/uploadReturnUrl"})
    public String uploadReturnUrl(@RequestParam("myFile") MultipartFile file, HttpServletRequest request) {
        String upload = upload(file,request);
        return fileComponent.getFileDownLoadUrl(request)+upload;
    }
    @PostMapping({"/uploadReturnGetUrl"})
    public String uploadReturnGetUrl(@RequestParam("myFile") MultipartFile file,HttpServletRequest request) {
        String upload = upload(file,request);
        return fileComponent.getPicturePath()+upload;
    }
    @PostMapping({"/uploadJson"})
    public ResponseVo uploadJson(@RequestParam("myFile") MultipartFile file, HttpServletRequest request) {
        String upload = upload(file,request);
        return ResponseVo.build(upload);
    }
    @PostMapping({"/upload"})
    public String upload(@RequestParam("myFile") MultipartFile file,HttpServletRequest request) {
        rolesLoginService.checkResource(request);
        // 获取原始名字
        String fileName = file.getOriginalFilename();
        // 获取后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 文件保存路径
        // 文件重命名，防止重复
        String fileUri = ComponentManager.getInstance().getUnionId() + suffixName;
        resourceComponent.addResource(fileName);
        fileName = fileComponent.getFileDir() + fileUri;
        // 文件对象
        File dest = new File(fileName);
        // 判断路径是否存在，如果不存在则创建
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            // 保存到服务器中
            file.transferTo(dest);
            return fileUri;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @GetMapping(value = FileComponent.DOWNLOAD)
    public void download(String fileName, HttpServletResponse response) {
        //要上传的文件名字
        //通过文件的保存文件夹路径加上文件的名字来获得文件
        File file = new File(fileComponent.getFileDir(), fileName);
        LOGGER.error("file:"+file.getPath());
        //当文件存在
        if (file.exists()) {
            //首先设置响应的内容格式是force-download，那么你一旦点击下载按钮就会自动下载文件了
            response.setContentType("application/force-download");
            //通过设置头信息给文件命名，也即是，在前端，文件流被接受完还原成原文件的时候会以你传递的文件名来命名
            response.addHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
            //进行读写操作
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                //从源文件中读
                int i = bis.read(buffer);
                while (i != -1) {
                    //写到response的输出流中
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //善后工作，关闭各种流
                try {
                    if (bis != null) {
                        bis.close();
                    }
                    if (fis != null) {
                        fis.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
