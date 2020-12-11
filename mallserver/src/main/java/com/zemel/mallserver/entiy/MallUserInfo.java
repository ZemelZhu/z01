package com.zemel.mallserver.entiy;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
/**
 * MallUserInfo 实体类
 * @Date:2020-07-15 23:26:39
 * @Authod zemel
*/
@Data
@TableName("mall_mall_user")
public class MallUserInfo {
	/** 用户id */
	private int id;
	/** 唯一id */
	private String unionId;
	/** 用户密码 */
	private String password;
	/** 用户名称 */
	private String nickName;
	/** 头像 */
	private String avatarUrl;
	/** 用户电话 */
	private String phone;
	/** 性别 */
	private boolean gender;
	/** 省份 */
	private String province;
	/** 城市 */
	private String city;
	/** 国家 */
	private String country;
	/** 用户地址 */
	private String address;
	/** 创建时间 */
	private Date createTime;
	/** 更新时间 */
	private Date updateTime;
	/** 状态 */
	private int status;
}

