package com.zemel.mallserver.entiy;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
/**
 * MallAfterSaleInfo 实体类
 * @Date:2020-07-15 23:26:39
 * @Authod zemel
*/
@Data
@TableName("mall_mall_after_sale")
public class MallAfterSaleInfo {
	/** 自增id */
	private int id;
	/** 全局id */
	private long transactionId;
	/** 创建者id */
	private int createId;
	/** 售后类型 */
	private int type;
	/** 联系人 */
	private String userName;
	/** 联系电话 */
	private String phone;
	/** 地址 */
	private String address;
	/** 图片 */
	private String picture;
	/** 描述 */
	private String description;
	/** 服务时间 */
	private Date supperTime;
	/** 产品类型 */
	private int productType;
	/** 产品id */
	private int productId;
	/** 物流信息 */
	private String express;
	/** 服务状态 */
	private int serviceStatus;
	/** 创建时间 */
	private Date createTime;
	/** 更新时间 */
	private Date updateTime;
	/** 状态 */
	private int status;
}

