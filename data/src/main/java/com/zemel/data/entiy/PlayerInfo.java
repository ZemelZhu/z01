package com.zemel.data.entiy;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
/**
 * PlayerInfo 实体类
 * @Date:2020-07-15 23:26:39
 * @Authod zemel
*/
@Data
@TableName("common_player")
public class PlayerInfo {
	/** 自增ID */
	private int id;
	/** 名称 */
	private String name;
	/** 密码 */
	private String password;
	/** 创建时间 */
	private Date createTime;
	/** 金币 */
	private BigDecimal coin;
	/** 类型 */
	private int type;
	/** 时间 */
	private Date time;
	/** 更新时间 */
	private Date updateTime;
	/** 状态信息 */
	private int status;
}

