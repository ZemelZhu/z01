package com.zemel.mallserver.services.common;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

import java.io.File;

/**
 * @Author: zemel
 * @Date: 2020/8/18 20:07
 */
public class QiNiuYunService1 {
    private static final String accessKey = "4H0bH1QjoZqCowUyN5lWqsD_ZEq7GsDTsiLHX4yf";
    private static final  String secretKey = "5E11KJP7apyet8kuI9JcJW79Z3XhLn0HZsSC3KEZ";
    public static void main(String[] args) throws Exception {
        File file = new File("D:\\13265\\Pictures\\picture\\test.jpg");

        //通过文件来传递
        QiNiuYunService.Result upload = upload( file, "sds.jpg");
        System.out.println(upload.getUrl());
    }
    public static QiNiuYunService.Result upload(File file,String name) {
        String bucketNm = "mallserver";
        return upload(bucketNm,file,name);
    }
    /**
     * 通过文件来传递数据
     * @param bucketNm
     * @param file
     * @return
     */
    public static QiNiuYunService.Result upload(String bucketNm, File file,String name) {
        QiNiuYunService.Result result = null;
        try {

            UploadManager uploadManager = getUploadManager(bucketNm);

            String token = getToken(bucketNm);
            Response response = uploadManager.put(file.getAbsolutePath(),name, token);

            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
//            System.out.println(putRet.key);
//            System.out.println(putRet.hash);

            result = new QiNiuYunService.Result(true,putRet.key);
        } catch (QiniuException e) {
            e.printStackTrace();
            result = new QiNiuYunService.Result(false);
        }
        return result;

    }
    /**
     * 获取上传管理器
     * @param bucketNm
     * @return
     */
    public static UploadManager getUploadManager(String bucketNm) {
        //构造一个带指定Zone对象的配置类
        //区域要和自己的bucket对上，不然就上传不成功
        //华东    Zone.zone0()
        //华北    Zone.zone1()
        //华南    Zone.zone2()
        //北美    Zone.zoneNa0()
        Configuration cfg = new Configuration(Zone.zone2());
        UploadManager uploadManager = new UploadManager(cfg);
        return uploadManager;
    }
    /**
     * 获取七牛云的上传Token
     * @param bucketNm
     * @return
     */
    public static String getToken(String bucketNm) {
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucketNm);
        return upToken;
    }
}
