package com.zemel.web1.entiy;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
/**
 * PlayerDiyInfo 实体类
 * @Date:2020-08-27 21:34:05
 * @Authod zemel
*/
@Data
@TableName("core_player_diy")
public class PlayerDiyInfo {
	/** 自增id */
	@TableId(type= IdType.AUTO)
	private int id;
	/** 玩家id */
	private int userId;
	/** diy信息 */
	private byte[] diyMessage;
	/** 创建时间 */
	private Date createTime;
	/** 更新时间 */
	private Date updateTime;
	/** 数据状态 */
	private int status;
	/** diy类型 */
	private int type;
	/** 二维码 */
	private String qrCode;
	/** 项目id */
	private int projectId;
	/** 音频 */
	private String audioSrc;
	/** 标题 */
	private String title;
	/** 页面比例 */
	private String screenProportion;
	/** 音频图片 */
	private String audioBgImageSrc;
	/** 标题图片 */
	private String titleBgImageSrc;
	/** 标题颜色 */
	private String pageTitleColor;
	/** 显示或隐藏标题栏 */
	private boolean showTitle;
}

