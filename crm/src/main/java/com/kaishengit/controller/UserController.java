package com.kaishengit.controller;

import com.kaishengit.exception.NotFoundException;
import com.kaishengit.pojo.User;
import com.kaishengit.service.UserService;
import com.kaishengit.util.ShiroUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

@RequestMapping("/user")
@Controller
public class UserController {

    @Inject
    private UserService userService;

    /**
     * 修改密码
     * @return
     */
    @RequestMapping(value = "/password",method = RequestMethod.GET)
    public String editPassword(){
        return "setting/password";
    }

    @RequestMapping(value = "/password",method = RequestMethod.POST)
    @ResponseBody
    public String editPassword(String password){
        userService.changePassword(password);
        return "success";
    }

    /**
     * 验证原密码是否正确(Ajax调用)
     */
    @RequestMapping(value = "/validate/password",method = RequestMethod.GET)
    @ResponseBody
    public String validateOldPassword(@RequestHeader("X-Requested-With") String xRequestedWith, String oldpassword){
        if ("XMLHttpRequest".equals(xRequestedWith)){
            User user = ShiroUtil.getCurrentUser();
            if (user.getPassword().equals(DigestUtils.md5Hex(oldpassword))){
                return "true";
            }
            return "false";
        }else{
            throw new NotFoundException();
        }

    }

}
