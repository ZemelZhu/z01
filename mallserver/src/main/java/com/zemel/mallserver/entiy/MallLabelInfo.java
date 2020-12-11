package com.zemel.mallserver.entiy;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
/**
 * MallLabelInfo 实体类
 * @Date:2020-07-15 23:26:39
 * @Authod zemel
*/
@Data
@TableName("mall_mall_label")
public class MallLabelInfo {
	/** 自增id */
	private int id;
	/** 标签名称 */
	private String labelName;
	/** 标签图片 */
	private String labelPicture;
	/** 数据状态 */
	private int status;
	/** 创建时间 */
	private Date createTime;
	/** 更新时间 */
	private Date updateTime;
	/** 是否成功案例 */
	private boolean successInfo;
	/** 排序 */
	private int labelIndex;
}

