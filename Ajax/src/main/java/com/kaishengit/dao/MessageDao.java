package com.kaishengit.dao;

import com.kaishengit.entity.Message;
import com.kaishengit.entity.User;
import com.kaishengit.util.DbHelp;
import com.kaishengit.util.cache.SimpleCache;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MessageDao {

    private Logger logger = LoggerFactory.getLogger(MessageDao.class);

    public List<Message> findAll() {
        String sql = "select * from t_message order by id desc";
        return DbHelp.query(sql, new BeanListHandler<>(Message.class));
    }

    public List<Message> findGtMaxId(String maxId) {
        String sql = "select * from t_message where id > ? order by id desc";
        return DbHelp.query(sql, new BeanListHandler<>(Message.class), maxId);
    }

    public Message findById(Integer id) {
        Message message = (Message) SimpleCache.get("message" + id);
        if (message == null) {
            String sql = "select * from t_message where id = ?";
            message = DbHelp.query(sql, new BeanHandler<>(Message.class), id);
            SimpleCache.set("message"+id,message);
        }else {
            logger.debug("form cache");
        }
        return message;
    }
}
