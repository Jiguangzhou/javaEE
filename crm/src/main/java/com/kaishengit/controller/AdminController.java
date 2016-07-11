package com.kaishengit.controller;

import com.google.common.collect.Maps;
import com.kaishengit.dto.DataTablesResult;
import com.kaishengit.dto.JSONResult;
import com.kaishengit.pojo.Role;
import com.kaishengit.pojo.User;
import com.kaishengit.service.UserService;
import com.kaishengit.util.Strings;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RequestMapping("/admin")
@Controller
public class AdminController {

    @Inject
    private UserService userService;

    @RequestMapping(value = "/userlist",method = RequestMethod.GET)
    public String list(Model model){

        List<Role> roleList = userService.findAllRole();
        model.addAttribute("roleList",roleList);
        return "admin/userlist";
    }

    @RequestMapping(value = "/load",method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult<User> loadUser(HttpServletRequest request){
        String draw = request.getParameter("draw");
        String start = request.getParameter("start");
        String length = request.getParameter("length");
        String keyword = request.getParameter("search[value]");
        keyword = Strings.toUTF8(keyword);

        Map<String,Object> param = Maps.newHashMap();
        param.put("start",start);
        param.put("length",length);
        param.put("keyword",keyword);

        List<User> userList = userService.findListByParam(param);
        Long count = userService.findUserCount();
        Long filtercount = userService.findCountByParam(param);

        return new DataTablesResult<>(draw,userList,count,filtercount);
    }

    /**
     * 添加新用户
     * @param user
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public String saveUser(User user){
        userService.saveUser(user);
        return "success";
    }

    /**
     * 验证用户名是否存在(Ajax调用)
     * @param username
     * @return
     */
    @RequestMapping(value = "/user/checkusername",method = RequestMethod.GET)
    @ResponseBody
    public String checkUsername(String username){
        User user = userService.findByUserName(username);
        if (user == null){
            return "true";
        }else {
            return "false";
        }
    }

    /**
     * 重置密码(000000)
     * @param id
     * @return
     */
    @RequestMapping(value = "/resetpassword",method = RequestMethod.POST)
    @ResponseBody
    public String resetPassword(Integer id){
        userService.resetUserPassword(id);
        return "success";
    }

    /**
     * 根据用户的ID显示用户JSON
     * @return
     */
    @RequestMapping(value = "/{id:\\d+}.json",method = RequestMethod.GET)
    @ResponseBody
    public JSONResult showUser(@PathVariable Integer id) {
        User user = userService.findUserById(id);
        if(user == null) {
            return new JSONResult("找不到"+id+"对应的用户");
        } else {
            return new JSONResult(user);
        }
    }
    /**
     * 编辑用户
     * @param user
     * @return
     */
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    public String editUser(User user) {
        userService.editUser(user);
        return "success";
    }

}
