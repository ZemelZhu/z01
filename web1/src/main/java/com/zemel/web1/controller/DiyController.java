package com.zemel.web1.controller;

import com.zemel.data.type.DataStatus;
import com.zemel.framework.ComponentManager;
import com.zemel.framework.exception.TipException;
import com.zemel.web1.ao.DiyAo;
import com.zemel.web1.entiy.model.PersistPlayerDiyInfo;
import com.zemel.web1.mapper.PlayerDiyInfoMapper;
import com.zemel.web1.service.DiyService;
import com.zemel.web1.vo.DiyVo;
import com.zemel.web1.vo.ProjectVo;
import com.zemel.web_framework.component.FileComponent;
import com.zemel.web_framework.component.ResourceComponent;
import com.zemel.web_framework.config.FileConfig;
import com.zemel.web_framework.mapper.UserInfoMapper;
import com.zemel.web_framework.utils.QRCodeUtils;
import com.zemel.web_framework.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author: zemel
 * @Date: 2020/7/18 12:24
 */
@RestController
@Api(tags = "Diy相关接口")
@RequestMapping("diy")
public class DiyController {
    @Resource
    private PlayerDiyInfoMapper playerDiyInfoMapper;
    @Resource
    private UserInfoMapper userInfoMapper;
    @Autowired
    private FileComponent fileComponent;
    @Autowired
    private FileConfig fileConfig;
    @Autowired
    private ResourceComponent resourceComponent;
    @Autowired
    private DiyService diyService;

    @PostMapping("createDiy")
    public DiyVo createDiy(DiyAo diyAo) {
        diyAo.check();
        int userId = SecurityUtils.getUserId();
        int projectId = diyAo.getProjectId();
        ProjectVo project = diyService.getProjectById(projectId);
        if(project.getUserId()!=userId)
            throw new TipException("不是你的项目");
        PersistPlayerDiyInfo playerDiyInfo = new PersistPlayerDiyInfo();
        diyAo.addCommon(playerDiyInfo);
        playerDiyInfo.setCreateTime(new Date());
        playerDiyInfo.setStatus(DataStatus.NORMAL.ordinal());
        playerDiyInfo.setType(0);
        playerDiyInfo.setProjectId(diyAo.getProjectId());
        playerDiyInfo.setUserId(userId);
        playerDiyInfo.setQrCode(ComponentManager.getInstance().getUnionId() + ".jpg");
        playerDiyInfoMapper.insert(playerDiyInfo);
//        List<String> resource = diyAo.getResource();
//        resourceComponent.confirmResource(resource);
        return diyService.getDiyById(playerDiyInfo.getId());
    }
    private void imageQR(PersistPlayerDiyInfo playerDiyInfo)
    {

            String pageUrl = fileComponent.getHttpHost() + "/#/pageShow?id=" + playerDiyInfo.getId();
            String fileDir = fileConfig.getFileDir();
            String destPath = fileDir;
//            QRCodeUtils.encode0(pageUrl,  null, playerDiyInfo.getQrCode());
            ComponentManager.getInstance().getCommonThreadPool().submit(()->{
                try {
                    QRCodeUtils.encode(pageUrl,null,destPath,false,playerDiyInfo.getQrCode());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
    }
    @ApiOperation("json格式diy")
    @PostMapping("diy")
    public DiyVo diy(@RequestBody DiyAo diyAo) {
        diyAo.check();
        PersistPlayerDiyInfo playerDiyInfo = diyService.getDiy(diyAo.getPageId());
        diyAo.addCommon(playerDiyInfo);
        try {
            playerDiyInfoMapper.updateById(playerDiyInfo);
        }catch (Exception e)
        {
            String message = diyAo.toString();
            throw new RuntimeException(message);
        }
        imageQR(playerDiyInfo);
        return diyService.getDiyById(playerDiyInfo.getId());
    }
    @ApiOperation("form格式diy")
    @PostMapping("diyForm")
    public DiyVo diyForm(DiyAo diyAo) {
        diyAo.check();
        PersistPlayerDiyInfo playerDiyInfo = diyService.getDiy(diyAo.getPageId());
        diyAo.addCommon(playerDiyInfo);
        try {

            playerDiyInfoMapper.updateById(playerDiyInfo);
            imageQR(playerDiyInfo);
        }catch (Exception e)
        {
            String message = diyAo.toString();
            throw new RuntimeException(message);
        }
        return diyService.getDiyById(playerDiyInfo.getId());
    }
    @DeleteMapping("deleteById")
    public boolean deleteById(int diyId) {
        PersistPlayerDiyInfo diy = diyService.getDiy(diyId);
        diy.setStatus(DataStatus.DELETE.ordinal());
        diy.setUpdateTime(new Date());
        playerDiyInfoMapper.updateById(diy);
        return true;
    }
    @GetMapping("getDiyById")
    public DiyVo getDiyById(int diyId) {
        return diyService.getDiyById(diyId);
    }
}
