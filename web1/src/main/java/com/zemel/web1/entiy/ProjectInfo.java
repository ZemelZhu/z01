package com.zemel.web1.entiy;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
/**
 * ProjectInfo 实体类
 * @Date:2020-12-12 23:00:17
 * @Authod zemel
*/
@Data
@TableName("core_project")
public class ProjectInfo {
	/** 自增id */
	@TableId(type= IdType.AUTO)
	private int id;
	/** 描述 */
	private String projectDesc;
	/** 开始限制时间 */
	private Date beginLimitTime;
	/** 结束限制时间 */
	private Date endLimitTime;
	/** 项目名 */
	private String projectName;
	/** 创建时间 */
	private Date createTime;
	/** 更新时间 */
	private Date updateTime;
	/** 数据状态 */
	private int status;
	/** 所属用户id */
	private int userId;
}

