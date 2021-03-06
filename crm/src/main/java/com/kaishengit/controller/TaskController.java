package com.kaishengit.controller;

import com.kaishengit.dto.JSONResult;
import com.kaishengit.pojo.Task;
import com.kaishengit.service.TaskService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Inject
    private TaskService taskService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model,Integer id){
        List<Task> timeoutTaskList = taskService.findTimeOutTask();
        model.addAttribute("timeoutTaskList",timeoutTaskList);

        List<Task> taskList = taskService.findTaskByUserId(id);
        model.addAttribute("taskList",taskList);
        return "task/list";
    }

    @RequestMapping(value = "/load",method = RequestMethod.GET)
    @ResponseBody
    public List<Task> load(String start,String end){
        return taskService.findTaskByUserId(start,end);
    }

    /**
     * 添加待办事项
     * @return
     */
    @RequestMapping(value = "/new",method = RequestMethod.POST)
    @ResponseBody
    public JSONResult newTask(Task task, String hour, String min){
        taskService.save(task,hour,min);
        return new JSONResult(task);
    }

    /**
     * 删除事项
     * @return
     */
    @RequestMapping(value = "/del/{id:\\d+}",method = RequestMethod.GET)
    @ResponseBody
    public String delTask(@PathVariable Integer id) {
        taskService.delTask(id);
        return "success";
    }

    /**
     * 将事项设置为已完成
     * @return
     */
    @RequestMapping(value = "/{id:\\d+}/done",method = RequestMethod.POST)
    @ResponseBody
    public JSONResult doneTask(@PathVariable Integer id) {
        Task task = taskService.doneTask(id);
        return new JSONResult(task);
    }

}
