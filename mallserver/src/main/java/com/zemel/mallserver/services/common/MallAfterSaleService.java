package com.zemel.mallserver.services.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zemel.data.type.QueryWrapperUtil;
import com.zemel.data.util.PageUtil;
import com.zemel.framework.exception.TipException;
import com.zemel.mallserver.ao.MallAfterSaleAo;
import com.zemel.mallserver.entiy.MallAfterSaleInfo;
import com.zemel.mallserver.mapper.MallAfterSaleInfoMapper;
import com.zemel.mallserver.type.OrderStatus;
import com.zemel.mallserver.vo.CommodityVo;
import com.zemel.mallserver.vo.MallAfterSaleVo;
import com.zemel.mallserver.vo.MallLabelVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author: zemel
 * @Date: 2020/5/2 17:22
 */
@Service
public class MallAfterSaleService {
    @Resource
    private MallAfterSaleInfoMapper mallAfterSaleInfoMapper;
    @Autowired
    private CommodityService commodityService;
    @Autowired
    private MallLabelService mallLabelService;

    public List<MallAfterSaleVo> getMallAfterSale(int page) {
        QueryWrapper<MallAfterSaleInfo> mallAfterSaleInfoQueryWrapper = QueryWrapperUtil.buildDataWrapper(MallAfterSaleInfo.class);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -60);
        Date time = c.getTime();
        mallAfterSaleInfoQueryWrapper.gt("create_time", time);
        Page<MallAfterSaleInfo> mallAfterSaleInfoPage = mallAfterSaleInfoMapper.selectPage(new Page<MallAfterSaleInfo>(page + 1, PageUtil.PAGE_SIZE), mallAfterSaleInfoQueryWrapper);
//        List<MallAfterSaleInfo> mallOrderInfoList = mallAfterSaleInfoMapper.selectByPage(page * 10);
        return transform(mallAfterSaleInfoPage.getRecords());
    }

    private List<MallAfterSaleVo> transform(List<MallAfterSaleInfo> mallAfterSaleInfos) {
        List<MallAfterSaleVo> mallAfterSaleVos = new ArrayList<>();
        for (MallAfterSaleInfo info : mallAfterSaleInfos) {
            mallAfterSaleVos.add(transform(info));
        }
        return mallAfterSaleVos;
    }

    private MallAfterSaleVo transform(MallAfterSaleInfo mallAfterSaleInfo) {
        CommodityVo commodityVo = commodityService.getCommodityById(mallAfterSaleInfo.getProductId());
        MallLabelVo labelVo = mallLabelService.getLabelById(mallAfterSaleInfo.getProductType());
        return new MallAfterSaleVo(mallAfterSaleInfo, commodityVo, labelVo);
    }


    public int getMallAllOrderCount() {
        return mallAfterSaleInfoMapper.selectAllCount();
    }

    public boolean createAfterSale(MallAfterSaleAo mallAfterSaleAo, int userId) {
        int labelId = mallAfterSaleAo.getLabelId();
        if (labelId != 0) {
            MallLabelVo labelById = mallLabelService.getLabelById(labelId);
            if (labelById == null)
                throw new TipException("找不到对应的标签");
        }
        MallAfterSaleInfo transform = mallAfterSaleAo.transform();
        transform.setCreateId(userId);
        return mallAfterSaleInfoMapper.insert(transform) > 0;
    }

    public boolean dealAfterSale(int id, OrderStatus status) {
        MallAfterSaleInfo mallAfterSaleInfo = mallAfterSaleInfoMapper.selectById(id);
        if (mallAfterSaleInfo != null) {
            mallAfterSaleInfo.setServiceStatus(status.ordinal());
            mallAfterSaleInfo.setUpdateTime(new Date());
            mallAfterSaleInfoMapper.updateById(mallAfterSaleInfo);
            return true;
        }
        return false;
    }

    public MallAfterSaleVo getAfterSaleById(long id) {
        MallAfterSaleInfo mallAfterSaleInfo = mallAfterSaleInfoMapper.selectById(id);
        if (mallAfterSaleInfo == null) {
            QueryWrapper<MallAfterSaleInfo> queryWrapper = new QueryWrapper<MallAfterSaleInfo>();
            queryWrapper.eq("transaction_id", id);
            mallAfterSaleInfo = mallAfterSaleInfoMapper.selectOne(queryWrapper);
        }
        if (mallAfterSaleInfo == null)
            return null;
        return transform(mallAfterSaleInfo);
    }

    public List<MallAfterSaleVo> getMyAfterSale(int userId) {
        List<MallAfterSaleInfo> mallAfterSaleInfos = mallAfterSaleInfoMapper.selectByCreateId(userId);
        return transform(mallAfterSaleInfos);
    }

    public List<MallAfterSaleVo> getMyAfterSale(int userId, int page) {
        QueryWrapper<MallAfterSaleInfo> mallAfterSaleInfoQueryWrapper = QueryWrapperUtil.buildDataWrapper(MallAfterSaleInfo.class);
        mallAfterSaleInfoQueryWrapper.eq("create_id", userId);
        Page<MallAfterSaleInfo> mallAfterSaleInfoPage = mallAfterSaleInfoMapper.selectPage(new Page<MallAfterSaleInfo>(page + 1, PageUtil.PAGE_SIZE), mallAfterSaleInfoQueryWrapper);
        return transform(mallAfterSaleInfoPage.getRecords());
    }
}
