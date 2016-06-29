package com.kaishengit.test;

import com.google.common.collect.Lists;
import com.kaishengit.mapper.NodeMapper;
import com.kaishengit.pojo.Node;
import com.kaishengit.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class NodeMapperTestCase {

    private Logger logger = LoggerFactory.getLogger(NodeMapperTestCase.class);

    @Test
    public void testbatchSave(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        NodeMapper nodeMapper = sqlSession.getMapper(NodeMapper.class);
        List<Node> nodeList = Lists.newArrayList();
        nodeList.add(new Node("java"));
        nodeList.add(new Node("idea"));
        nodeList.add(new Node("commons"));
        nodeList.add(new Node("apache"));
        nodeList.add(new Node("google"));

        nodeMapper.batchSave(nodeList);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testfindByIds(){


        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        NodeMapper nodeMapper = sqlSession.getMapper(NodeMapper.class);
        List<Integer> idList = Lists.newArrayList(1,2,3);
        List<Node> nodeList = nodeMapper.findByIds(idList);

        for (Node node:nodeList){
            logger.debug("{}",node);
        }
        sqlSession.close();

    }

    @Test
    public void testNodefindById(){
        //一级缓存:在同一个SqlSession中多次查询一个对象，会触发一级缓存

        //二级缓存：在同一个SqlSessionFactory产生的SQLsession中多次查询同一个对象，会触发二级缓存
        //二级缓存需要配置才能够有生效：1.放入的对象要是能够可序列化的 2.在mapper.xml中添加<cache/>
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        NodeMapper nodeMapper = sqlSession.getMapper(NodeMapper.class);

        Node node = nodeMapper.findById(1);

        node = nodeMapper.findById(1);
        node = nodeMapper.findById(1);
        node = nodeMapper.findById(1);

        logger.debug("{}",node);

        sqlSession.close();


    }
}
