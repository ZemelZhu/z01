package com.zemel.mallserver.entiy;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
/**
 * AdminUserInfo 实体类
 * @Date:2020-07-15 23:26:39
 * @Authod zemel
*/
@Data
@TableName("mall_admin_user")
public class AdminUserInfo {
	/** 用户管理员 */
	private int id;
	/** 管理员名称 */
	private String name;
	/** 管理员头像 */
	private String avatar;
	/** 管理员密码 */
	private String password;
	/** 管理员状态 */
	private int status;
	/** 管理员类型 */
	private int type;
	/** 创建时间 */
	private Date createTime;
	/** 更新时间 */
	private Date updateTime;
}

