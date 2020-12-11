package com.zemel.data.mapping;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zemel.data.entiy.PlayerInfo;
import org.apache.ibatis.annotations.Mapper;

public interface PlayerInfoMapper extends BaseMapper<PlayerInfo> {
//    @Select("select * from player_info where id = #{id}")
//    public PlayerInfo1 getPlayerInfo1(int id);
//    @Insert({ "insert into player_info( name, create_time, coin) values( #{name}, #{createTime},#{coin})" })
//    @Options(useGeneratedKeys = true, keyProperty = "id")
//    public int save(PlayerInfo playerInfo);
}