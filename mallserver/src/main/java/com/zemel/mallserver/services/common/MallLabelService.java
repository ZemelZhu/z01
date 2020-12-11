package com.zemel.mallserver.services.common;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zemel.data.type.DataStatus;
import com.zemel.data.type.QueryWrapperUtil;
import com.zemel.framework.until.JsonUntil;
import com.zemel.framework.until.StringUtil;
import com.zemel.mallserver.ao.LabelAo;
import com.zemel.mallserver.component.FileComponent;
import com.zemel.mallserver.entiy.MallConfigInfo;
import com.zemel.mallserver.entiy.MallLabelInfo;
import com.zemel.mallserver.mapper.MallConfigInfoMapper;
import com.zemel.mallserver.mapper.MallLabelInfoMapper;
import com.zemel.mallserver.type.MallConfigType;
import com.zemel.mallserver.vo.MallLabelVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: zemel
 * @Date: 2020/4/5 11:45
 */
@Service
public class MallLabelService {
    @Resource
    private MallLabelInfoMapper mallLabelInfoMapper;
    @Autowired
    private FileComponent fileComponent;
    @Resource
    private MallConfigInfoMapper mallConfigInfoMapper;

    public MallLabelVo getLabelById(int id) {
        MallLabelInfo mallLabelInfo = mallLabelInfoMapper.selectById(id);
        if(mallLabelInfo==null)
            return null;
        return new MallLabelVo(mallLabelInfo, fileComponent.getPicturePath());
    }
    public List<MallLabelVo> getLabelMaps() {
        QueryWrapper<MallLabelInfo> mallLabelInfoQueryWrapper = QueryWrapperUtil.buildDataWrapper(MallLabelInfo.class);
        List<MallLabelInfo> mallLabelInfos = mallLabelInfoMapper.selectList(mallLabelInfoQueryWrapper);
        mallLabelInfos = mallLabelInfoMapper.selectList(mallLabelInfoQueryWrapper);
        String picturePath = fileComponent.getPicturePath();
        List<MallLabelVo> collect = mallLabelInfos.stream().map(f -> new MallLabelVo(f, picturePath)).collect(Collectors.toList());
        collect.sort(null);
        return collect;
    }

    public Collection<MallLabelVo> homeLabel() {
        return getLabelMaps();
    }

    public boolean deleteLabel(int id) {
        MallLabelInfo mallLabelInfo = mallLabelInfoMapper.selectById(id);
        if (mallLabelInfo != null && mallLabelInfo.getStatus() == DataStatus.NORMAL.ordinal()) {
            mallLabelInfo.setStatus(DataStatus.DELETE.ordinal());
            mallLabelInfo.setUpdateTime(new Date());
            if (mallLabelInfoMapper.updateById(mallLabelInfo) > 0) {
                return true;
            }
        }
        return false;
    }

    public boolean addLabel(LabelAo labelAo) {
        if (labelAo != null && !StringUtil.isNullOrEmpty(labelAo.getLabelName()) && !StringUtil.isNullOrEmpty(labelAo.getLabelPicture())) {
            MallLabelInfo mallLabelInfo = labelAo.getMallLabelInfo();
            if (mallLabelInfoMapper.insert(mallLabelInfo) > 0) {
                return true;
            }
        }
        return false;
    }

    public List<Object> getMallConfig() {
        List<MallConfigInfo> mallConfigInfos = mallConfigInfoMapper.selectList((Wrapper<MallConfigInfo>) QueryWrapperUtil.buildDataWrapper(MallConfigInfo.class));
        return mallConfigInfos.stream().map(f -> JsonUntil.stringToObject(f.getValue(), Object.class)).collect(Collectors.toList());
    }

    public Object getMallConfigById(int id) {
        MallConfigInfo mallConfigInfo = mallConfigInfoMapper.selectById(id);
        if (mallConfigInfo == null)
            return null;
        return JsonUntil.stringToObject(mallConfigInfo.getValue(), Object.class);
    }

    public boolean setConfig(MallConfigType type, Object object) {
        String value = JsonUntil.objectToString(object);
        int id = type.ordinal();
        MallConfigInfo mallConfigInfo = mallConfigInfoMapper.selectById(id);
        if (mallConfigInfo == null) {
            mallConfigInfo = new MallConfigInfo();
            mallConfigInfo.setId(id);
            mallConfigInfo.setCreateTime(new Date());
            mallConfigInfo.setUpdateTime(new Date());
            mallConfigInfo.setStatus(DataStatus.NORMAL.ordinal());
            mallConfigInfo.setValue(value);
            mallConfigInfoMapper.insert(mallConfigInfo);
        } else {
            mallConfigInfo.setValue(value);
            mallConfigInfo.setUpdateTime(new Date());
            mallConfigInfoMapper.updateById(mallConfigInfo);
        }
        return true;
    }

    public List<Integer> getLabelBySearch(String match) {
        QueryWrapper<MallLabelInfo> mallLabelInfoQueryWrapper = QueryWrapperUtil.buildDataWrapper(MallLabelInfo.class);
        mallLabelInfoQueryWrapper.like("label_name", match);
        List<MallLabelInfo> mallLabelInfos = mallLabelInfoMapper.selectList(mallLabelInfoQueryWrapper);
        return mallLabelInfos.stream().map(f -> f.getId()).collect(Collectors.toList());
    }

    public boolean setSuccessLabel(int id) {
        QueryWrapper<MallLabelInfo> mallLabelInfoQueryWrapper = QueryWrapperUtil.buildDataWrapper(MallLabelInfo.class);
        List<MallLabelInfo> mallLabelInfos = mallLabelInfoMapper.selectList(mallLabelInfoQueryWrapper);
        for(MallLabelInfo info:mallLabelInfos)
        {
            if(info.getId() ==id)
                info.setSuccessInfo(true);
            else
                info.setSuccessInfo(false);
            mallLabelInfoMapper.updateById(info);
        }
        return true;
    }

    public boolean updateIndex(int id, int index) {
        MallLabelInfo mallLabelInfo = mallLabelInfoMapper.selectById(id);
        if(mallLabelInfo!=null)
        {
            mallLabelInfo.setLabelIndex(index);
            mallLabelInfoMapper.updateById(mallLabelInfo);
            return true;
        }
        return false;
    }

    public boolean updateIndex(LabelAo labelAo) {
        MallLabelInfo mallLabelInfo = mallLabelInfoMapper.selectById(labelAo.getId());
        if(mallLabelInfo!=null)
        {
            if(labelAo.getLabelName()!=null&&labelAo.getLabelName().length()>0)
                mallLabelInfo.setLabelName(labelAo.getLabelName());
            if(labelAo.getLabelPicture()!=null&&labelAo.getLabelPicture().length()>0)
            mallLabelInfo.setLabelPicture(labelAo.getLabelPicture());
            if(labelAo.getLabelIndex()>0)
                mallLabelInfo.setLabelIndex(labelAo.getLabelIndex());
            mallLabelInfoMapper.updateById(mallLabelInfo);
            return true;
        }
        return false;
    }
}
