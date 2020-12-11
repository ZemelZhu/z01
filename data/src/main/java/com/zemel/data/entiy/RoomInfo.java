package com.zemel.data.entiy;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
/**
 * RoomInfo 实体类
 * @Date:2020-07-07 21:30:56
 * @Authod zemel
*/
@Data
@TableName("core_room")
public class RoomInfo {
	/** id */
	private int id;
	/** 房间号 */
	private int roomPassword;
	/** 游戏类型 */
	private int gameType;
	/** 房间类型 */
	private int roomType;
	/** 房间最大人数 */
	private int playerCount;
	/** 房间当前人数 */
	private int currentPlayerCount;
	/** 创建者ID */
	private int creatorId;
	/** 创建时间 */
	private Date createTime;
	/** 更新时间 */
	private Date updateTime;
	/** 数据状态 */
	private int status;
	/** 房间状态 */
	private int roomStatus;
}

