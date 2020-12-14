package com.zemel.web1.entiy;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
/**
 * ResourceInfo 实体类
 * @Date:2020-12-12 23:00:17
 * @Authod zemel
*/
@Data
@TableName("core_resource")
public class ResourceInfo {
	/** 自增id */
	@TableId(type= IdType.AUTO)
	private int id;
	/** 名称 */
	private String name;
	/** 资源 */
	private String resource;
	/** 类型 */
	private int type;
	/** 创建时间 */
	private Date createTime;
	/** 更新时间 */
	private Date updateTime;
	/** 数据状态 */
	private int status;
	/** 玩家id */
	private int userId;
	/** 项目id */
	private int projectId;
}

