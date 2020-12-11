package com.zemel.mallserver.services.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.zemel.data.type.DataStatus;
import com.zemel.data.type.QueryWrapperUtil;
import com.zemel.data.util.PageUtil;
import com.zemel.framework.exception.TipException;
import com.zemel.framework.until.RandomUtil;
import com.zemel.mallserver.ao.CommodityAo;
import com.zemel.mallserver.entiy.CommodityInfo;
import com.zemel.mallserver.mapper.CommodityInfoMapper;
import com.zemel.mallserver.vo.CommodityVo;
import com.zemel.mallserver.vo.MallLabelVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: zemel
 * @Date: 2020/3/22 11:36
 */
@Service
public class CommodityService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommodityService.class);
    private static final int defaultPageSize = 10;
    private static final int homeCarouselMax = 10;
    @Resource
    private CommodityInfoMapper commodityInfoMapper;
    @Autowired
    private MallLabelService mallLabelService;


    public boolean createOrder(CommodityAo commodityDto) throws JsonProcessingException {
        int label = commodityDto.getLabel();
        MallLabelVo mallLabelVo = mallLabelService.getLabelById(label);
        if (mallLabelVo == null)
            throw new TipException("系统找不到该标签");
        CommodityInfo commodityInfo = commodityDto.tranInfo();
        int id = commodityInfoMapper.insert(commodityInfo);
        if (id > 0) {
            return true;
        }
        return false;
    }
    private List<CommodityVo> transform(List<CommodityInfo> mallOrderInfos)
    {
        List<CommodityVo> collect = mallOrderInfos.stream().map(f -> new CommodityVo(f, mallLabelService.getLabelById(f.getLabel()))).collect(Collectors.toList());
        return collect;
    }
    public List<CommodityVo> getMallShop(int page) {
        QueryWrapper<CommodityInfo> queryWrapper = QueryWrapperUtil.buildDataWrapper(CommodityInfo.class);
        Page<CommodityInfo> commodityInfoPage = commodityInfoMapper.selectPage(new Page<CommodityInfo>(page+1, PageUtil.PAGE_SIZE), queryWrapper);
        return transform(commodityInfoPage.getRecords());
    }

    public boolean delete(int id) {
        CommodityInfo commodityInfo = commodityInfoMapper.selectById(id);
        if (commodityInfo != null) {
            commodityInfo.setStatus(DataStatus.DELETE.ordinal());
            commodityInfo.setUpdateTime(new Date());
            commodityInfoMapper.updateById(commodityInfo);
            return true;
        }
        return false;
    }

    public CommodityVo getCommodityById(long id) {

        CommodityInfo commodityInfo = commodityInfoMapper.selectById(id);
        if(commodityInfo==null)
        {
            QueryWrapper<CommodityInfo> queryWrapper = new QueryWrapper<CommodityInfo>();
            queryWrapper.eq("transaction_id",id);
            commodityInfo = commodityInfoMapper.selectOne(queryWrapper);
        }
        if(commodityInfo==null)
            return null;
        CommodityVo commodityVo = new CommodityVo(commodityInfo, mallLabelService.getLabelById(commodityInfo.getLabel()));
        return commodityVo;

    }
    public CommodityVo getCommodityById(int commodityId) {

        CommodityInfo commodityInfo = commodityInfoMapper.selectById(commodityId);
        if(commodityInfo==null)
            return null;
        CommodityVo commodityVo = new CommodityVo(commodityInfo, mallLabelService.getLabelById(commodityInfo.getLabel()));

        return commodityVo;
    }

    public boolean mask(int id) {
        CommodityInfo commodityInfo = commodityInfoMapper.selectById(id);
        if (commodityInfo != null) {
            int mask = 1 << CommodityVo.MaskStatus.IS_HOME_CAROUSEL.ordinal();
            commodityInfo.setMask(commodityInfo.getMask() ^ mask);
            commodityInfoMapper.updateById(commodityInfo);
            return true;
        }
        return false;
    }

    public List<CommodityVo> homeCarousel() {
        int mask = 1 << CommodityVo.MaskStatus.IS_HOME_CAROUSEL.ordinal();
        List<CommodityInfo> commodityInfos = commodityInfoMapper.selectMask(mask);
        List<CommodityVo> collect = commodityInfos.stream().map(f -> new CommodityVo(f, mallLabelService.getLabelById(f.getLabel()))).collect(Collectors.toList());
        int limit = RandomUtil.rand(homeCarouselMax);
        return RandomUtil.rand(collect, limit);
    }

    public int getMallAllShopCount() {
        return commodityInfoMapper.selectAllCount();
    }

    public List<CommodityVo> getCommodityBySearch(int page, String match) {
        List<Integer> labelBySearch = mallLabelService.getLabelBySearch(match);

        QueryWrapper<CommodityInfo> queryWrapper = QueryWrapperUtil.buildDataWrapper(CommodityInfo.class);
        queryWrapper.like("name",match).or().like("description",match).or().in("label",labelBySearch);
        Page<CommodityInfo> commodityInfoPage = commodityInfoMapper.selectPage(new Page<CommodityInfo>(page+1, PageUtil.PAGE_SIZE), queryWrapper);
        return transform(commodityInfoPage.getRecords());
    }

    public List<CommodityVo> getMallShopByLabel(int page, int label) {
        QueryWrapper<CommodityInfo> queryWrapper =  QueryWrapperUtil.buildDataWrapper(CommodityInfo.class);
        queryWrapper.eq("label",label);
        Page<CommodityInfo> commodityInfoPage = commodityInfoMapper.selectPage(new Page<CommodityInfo>(page+1, PageUtil.PAGE_SIZE), queryWrapper);
        return transform(commodityInfoPage.getRecords());
    }

    public List<CommodityVo> getMallShopSuccessLabel(int page) {
        List<MallLabelVo> labelMaps = mallLabelService.getLabelMaps();
        for(MallLabelVo vo:labelMaps)
        {
            if(vo.isFirstLabel())
            {   QueryWrapper<CommodityInfo> queryWrapper =  QueryWrapperUtil.buildDataWrapper(CommodityInfo.class);
                queryWrapper.eq("label",vo.getId());
                Page<CommodityInfo> commodityInfoPage = commodityInfoMapper.selectPage(new Page<CommodityInfo>(page+1, PageUtil.PAGE_SIZE), queryWrapper);
                return transform(commodityInfoPage.getRecords());
            }
        }
       return new ArrayList<>();
    }
}
