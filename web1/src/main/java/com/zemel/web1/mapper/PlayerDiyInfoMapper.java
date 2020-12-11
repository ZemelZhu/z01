package com.zemel.web1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zemel.web1.entiy.PlayerDiyInfo;
import com.zemel.web1.entiy.model.PersistPlayerDiyInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: zemel
 * @Date: 2020/7/18 14:03
 */
public interface PlayerDiyInfoMapper extends BaseMapper<PersistPlayerDiyInfo> {
    @Select("select * from core_player_diy where project_id = #{projectId} and status=0")
    List<PlayerDiyInfo> selectAllByProjectId(int projectId);
}
