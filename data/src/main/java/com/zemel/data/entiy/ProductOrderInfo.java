package com.zemel.data.entiy;

import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
/**
 * ProductOrderInfo 实体类
 * @Date:2020-07-07 21:30:56
 * @Authod zemel
*/
@Data
@TableName("mall_product_order")
public class ProductOrderInfo {
	/** 订单id */
	private int id;
	/** 订单全局唯一id */
	private long transactionId;
	/** 物流信息 */
	private String express;
	/** 产品id */
	private int productId;
	/** 用户id */
	private int createId;
	/** 收货地址 */
	private String address;
	/** 收件人电话 */
	private String phone;
	/** 收件人 */
	private String name;
	/** 创建时间 */
	private Date createTime;
	/** 支付时间 */
	private Date payTime;
	/** 更新时间 */
	private Date updateTime;
	/** 状态 */
	private int status;
	/** 订单标记 */
	private int mask;
	/** 渠道id */
	private int channelId;
	/** 设备id */
	private int device;
	/** 支付总价格 */
	private BigDecimal payPrice;
	/** 数量 */
	private int amount;
	/** 订单状态(0创建,1同意,2拒绝) */
	private int orderStatus;
	/** 描述 */
	private String description;
	/** 图片 */
	private String picture;
}

