package com.zemel.data.entiy;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
/**
 * ServerClusterInfo 实体类
 * @Date:2020-07-07 21:30:56
 * @Authod zemel
*/
@Data
@TableName("core_server_cluster")
public class ServerClusterInfo {
	/** 自增ID */
	private int id;
	/** 服务器类型 */
	private int serverType;
	/** 服务器状态 */
	private int serverState;
	/** 名称 */
	private String name;
	/** 内网ip */
	private String innerIp;
	/** 外网ip */
	private String outIp;
	/** 路由 */
	private String address;
	/** 端口号 */
	private int port;
	/** 数据状态 */
	private int status;
	/** 更新时间 */
	private Date updateTime;
	/** 创建时间 */
	private Date createTime;
}

