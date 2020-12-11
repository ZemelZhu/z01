package com.zemel.mallserver.controller.admin;

import com.zemel.data.type.Roles;
import com.zemel.framework.annotation.RolesAnnotation;
import com.zemel.framework.exception.TipException;
import com.zemel.framework.until.StringUtil;
import com.zemel.framework.vo.ResponseVo;
import com.zemel.mallserver.ao.LabelAo;
import com.zemel.mallserver.ao.OfficialLinkAo;
import com.zemel.mallserver.component.FileComponent;
import com.zemel.mallserver.services.common.MallLabelService;
import com.zemel.mallserver.type.MallConfigType;
import com.zemel.mallserver.type.QQVidelUtil;
import com.zemel.mallserver.vo.FirstVideoVo;
import com.zemel.mallserver.vo.HomePictureVo;
import com.zemel.mallserver.vo.MallLabelVo;
import com.zemel.mallserver.vo.SalePictureVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author: zemel
 * @Date: 2020/4/5 11:58
 */
@RolesAnnotation(roles = Roles.ADMIN)
@RestController
@CrossOrigin
@RequestMapping("admin/mall/label")
public class AdminMallLabelController {
    @Autowired
    private MallLabelService mallLabelService;
    @Autowired
    private FileComponent fileComponent;
    @GetMapping("homeLabel")
    public Collection<MallLabelVo> homeLabel() {
        Collection<MallLabelVo> mallLabelInfos = mallLabelService.homeLabel();
        return mallLabelInfos;
    }

    @PostMapping("createLabel")
    public ResponseVo createLabel(LabelAo labelAo) {
        if (!StringUtil.isNullOrEmpty(labelAo.getLabelName()) && !StringUtil.isNullOrEmpty(labelAo.getLabelPicture())) {
            return ResponseVo.build(mallLabelService.addLabel(labelAo));
        }
        return ResponseVo.build(false);
    }

    @PostMapping("deleteLabel")
    public boolean deleteLabel(int id) {
        return mallLabelService.deleteLabel(id);

    }
    @PostMapping("firstVideo")
    public boolean firstVideo(String url) {
        if(StringUtil.isNullOrEmpty(url))
            throw new TipException("链接不能为空");
        String[] split = url.split(",");
        List<String> urls = new ArrayList<>();
        List<String> codes = new ArrayList<>();
        try {
            for(int i= 0;i<split.length;i++)
            {
                urls.add(split[i]);
                codes.add(QQVidelUtil.getCode(split[i]));
            }
        }
        catch (Exception E)
        {
            throw new TipException("链接解析不出视频码");
        }
        FirstVideoVo firstVideoVo = new FirstVideoVo();
        firstVideoVo.setVideoUrl(url);
        firstVideoVo.setVideoCode(String.join(",",codes));
        firstVideoVo.setVideoUrls(urls);
        firstVideoVo.setVideoCodes(codes);
        firstVideoVo.setId(MallConfigType.FIRST_VIDEO.ordinal());
        return mallLabelService.setConfig(MallConfigType.FIRST_VIDEO,firstVideoVo);
    }
    @PostMapping("officialLink")
    public boolean OfficialLink(OfficialLinkAo officialLinkAo)
    {
        officialLinkAo.check();
        officialLinkAo.setId(MallConfigType.OFFICIAL_LINK.ordinal());
        return mallLabelService.setConfig(MallConfigType.OFFICIAL_LINK,officialLinkAo);
    }
    @PostMapping("salePicuture")
    public boolean salePicuture(String url)
    {
        int id = MallConfigType.SALE_PICTURE.ordinal();
        SalePictureVo salePictureVo = new SalePictureVo();
        salePictureVo.setId(id);
        salePictureVo.setPictureCode(fileComponent.getPicture(url));
        salePictureVo.setUrl(fileComponent.getPictureUrl(url));
        return mallLabelService.setConfig(MallConfigType.SALE_PICTURE,salePictureVo);
    }
    @PostMapping("setSuccessLabel")
    public boolean setSuccessLabel(int id)
    {
        return mallLabelService.setSuccessLabel(id);
    }
    @PostMapping("updateIndex")
    public boolean updateIndex(LabelAo labelAo)
    {
        return mallLabelService.updateIndex(labelAo);
    }
    @PostMapping("homePictures")
    public boolean homePictures(String url)
    {
        int id = MallConfigType.HOME_PICTURE.ordinal();
        HomePictureVo homePictureVo = new HomePictureVo();
        homePictureVo.setId(id);
        homePictureVo.setPictureCode(fileComponent.getPicture(url));
        homePictureVo.setUrl(fileComponent.getPictureUrl(url));
        return mallLabelService.setConfig(MallConfigType.HOME_PICTURE,homePictureVo);
    }
}
