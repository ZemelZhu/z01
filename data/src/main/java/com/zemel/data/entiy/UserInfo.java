package com.zemel.data.entiy;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
/**
 * UserInfo 实体类
 * @Date:2020-08-27 21:34:05
 * @Authod zemel
*/
@Data
@TableName("common_user")
public class UserInfo {
	/** 自增id */
	@TableId(type= IdType.AUTO)
	private int id;
	/** 用户名 */
	private String userName;
	/** 凭证 */
	private String token;
	/** 密码 */
	private String password;
	/** 创建时间 */
	private Date createTime;
	/** 更新时间 */
	private Date updateTime;
	/** 数据状态 */
	private int status;
	/** 上一次登录时间 */
	private Date loginTime;
	/** 用户类型 */
	private int userType;
	/** 用户状态 */
	private int userStatus;
}

