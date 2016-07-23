package com.kaishengit.service;

import com.kaishengit.mapper.TaskMapper;
import com.kaishengit.pojo.Task;
import com.kaishengit.util.ShiroUtil;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
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

    /**
     * 显示待办事项
     * @param start
     * @param end
     * @return
     */
    public List<Task> findTaskByUserId(String start,String end) {
        return taskMapper.findByUserIdAndDateRanger(ShiroUtil.getCurrentUserID(),start,end);
    }

    /**
     * 添加待办
     * @param task
     * @param hour
     * @param min
     */
    public void save(Task task, String hour, String min) {
        if (StringUtils.isNotEmpty(hour) && StringUtils.isNotEmpty(min)){
            String reminderTime = task.getStart() + " "+hour + ":" + min + ":00";
            logger.debug("提醒时间为:{}",reminderTime);
            //
            task.setRemindertime(reminderTime);
        }
        task.setUserid(ShiroUtil.getCurrentUserID());
        taskMapper.save(task);
    }

    /**
     * 获取当前用户已经超时的事项
     * @return
     */
    public List<Task> findTimeOutTask() {
        String today = DateTime.now().toString("yyyy-MM-dd");
        return taskMapper.findTimeOutTask(ShiroUtil.getCurrentUserID(),today);
    }

    /**
     * 删除事项
     * @param id
     */
    public void delTask(Integer id) {
        taskMapper.del(id);
    }

    /**
     * 将事项设置为已完成
     * @param id
     */
    public Task doneTask(Integer id) {
        Task task = taskMapper.findById(id);
        task.setDone(true);
        task.setColor("#9c977d");
        taskMapper.update(task);
        return task;
    }

    public List<Task> findTaskByUserId(Integer id) {
        return taskMapper.findByUserId(id);
    }
}
