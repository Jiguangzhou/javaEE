package com.kaishengit.test;

import com.kaishengit.mapper.MovieMapper;
import com.kaishengit.pojo.Movie;
import com.kaishengit.pojo.User;
import com.kaishengit.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MovieMapperTestCase {

    private Logger logger = LoggerFactory.getLogger(MovieMapperTestCase.class);

    @Test
    public void testfindById(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        MovieMapper movieMapper = sqlSession.getMapper(MovieMapper.class);

        Movie movie = movieMapper.findById(1);

        logger.debug("{}",movie);
        //${movie.user.username} ${movie.node.nodename}
        logger.debug("Username:{} Address:{}",movie.getUser().getUsername(),movie.getUser().getAddress());
        logger.debug("Nodename:{}",movie.getNode().getNodename());
        sqlSession.close();
    }
}
