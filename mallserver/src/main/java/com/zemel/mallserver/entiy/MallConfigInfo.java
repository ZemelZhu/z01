package com.zemel.mallserver.entiy;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
/**
 * MallConfigInfo 实体类
 * @Date:2020-07-15 23:26:39
 * @Authod zemel
*/
@Data
@TableName("mall_mall_config")
public class MallConfigInfo {
	/** 配置id */
	private int id;
	/** 配置内容 */
	private String value;
	/** 创建时间 */
	private Date createTime;
	/** 更新时间 */
	private Date updateTime;
	/** 数据状态 */
	private int status;
}

