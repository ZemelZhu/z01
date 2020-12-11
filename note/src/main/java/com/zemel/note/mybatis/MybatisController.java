package com.zemel.note.mybatis;

import com.zemel.data.entiy.PlayerInfo;
import com.zemel.data.mapping.PlayerInfoMapper;
import com.zemel.framework.until.SpringUtils;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zemel
 * @Date: 2020/2/22 15:36
 */
@RestController
@RequestMapping("mybatis")
public class MybatisController {
    @Resource
    private PlayerInfoMapper playerInfoMapper;
    @RequestMapping("testSqlSession")
    public List<PlayerInfo> testSqlSession(int id)
    {
        List<PlayerInfo> playerInfos = new ArrayList<>();
        playerInfos.add(playerInfoMapper.selectById(id));
        playerInfos.add(playerInfoMapper.selectById(id));
        return playerInfos;
    }
    @RequestMapping("testSqlSession2")
    public  void testSqlSession2(int id)
    {
        MapperFactoryBean<PlayerInfo> bean = (MapperFactoryBean<PlayerInfo>) SpringUtils.getBean("&playerInfoMapper");
        SqlSession sqlSession = bean.getSqlSession();
        List<Object> objects = sqlSession.selectList("select * ");
        objects = sqlSession.selectList("select * ");
        PlayerInfoMapper mapper = sqlSession.getMapper(PlayerInfoMapper.class);
        if(bean!=null)
        {
            System.out.println(bean.toString());
        }
        else
        {
            System.out.println("null");
        }
        List<PlayerInfo> playerInfos = new ArrayList<>();
//        playerInfos.add(mapper.selectById(id));
//        playerInfos.add(mapper.selectById(id));
//        return playerInfos;
    }
}
