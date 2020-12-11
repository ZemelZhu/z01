package com.zemel.mallserver.entiy;

import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
/**
 * CommodityInfo 实体类
 * @Date:2020-07-15 23:26:39
 * @Authod zemel
*/
@Data
@TableName("mall_commodity")
public class CommodityInfo {
	/** 商品id */
	private int id;
	/** 商品名称 */
	private String name;
	/** 商品标签 */
	private int label;
	/** 原始数量 */
	private int baseCount;
	/** 商品数量 */
	private int count;
	/** 商品折扣价格 */
	private BigDecimal discountPrice;
	/** 商品金额 */
	private BigDecimal price;
	/** 商品描述 */
	private String description;
	/** 商品视频 */
	private String video;
	/** 商品图片 */
	private String picture;
	/** 创建时间 */
	private Date createTime;
	/** 修改时间 */
	private Date updateTime;
	/** 状态 */
	private int status;
	/** 状态掩码 */
	private int mask;
	/** 描述图片 */
	private String descPicture;
}

