package com.kaishengit.service;

import com.kaishengit.mapper.TaskMapper;
import com.kaishengit.pojo.Task;
import com.kaishengit.util.ShiroUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class TaskService {

    @Inject
    private TaskMapper taskMapper;

    private Logger logger = LoggerFactory.getLogger(TaskService.class);

    public List<Task> findTaskByUserId() {
        return taskMapper.findByUserId(ShiroUtil.getCurrentUserID());
    }

    /**
     * 添加待办
     * @param task
     * @param hour
     * @param min
     */
    public void save(Task task, String hour, String min) {
        if (StringUtils.isNotEmpty(hour) && StringUtils.isNotEmpty(min)){
            String reminderTime = task.getStart()+""+hour+":"+min;
            logger.debug("提醒时间为:{}",reminderTime);
            //
            task.setRemindertime(reminderTime);
        }
        task.setUserid(ShiroUtil.getCurrentUserID());
        taskMapper.save(task);
    }

    public List<Task> findTimeOutTask() {

    }
}
