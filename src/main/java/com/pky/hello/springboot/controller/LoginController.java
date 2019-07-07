package com.pky.hello.springboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.pky.hello.springboot.commons.domain.TbUser;
import com.pky.hello.springboot.commons.service.LoginService;
import com.pky.hello.springboot.commons.utils.BeanValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping(value = "users")
public class LoginController {

    @Autowired
    LoginService loginService;

    /**
     * 登录页
     * @return
     */
    @GetMapping(value = "")
    public String login() {
        return "login";
    }

    @PostMapping(value = "login")
    public String login(String username, String password) {
        System.out.println("登陆页");
        return "login";
    }

    @GetMapping(value = "login")
    public JSONObject login(TbUser tbUser) {

        JSONObject jsonObject = new JSONObject();
        // 数据校验
        String message = BeanValidator.validator(tbUser);
        if(StringUtils.isNotBlank(message)) {
            jsonObject.put("msg", message);
            return jsonObject;
        }
        // 登录校验
        TbUser user = loginService.getByLoginId(tbUser);
        // 登录成功
        if(user != null) {
            jsonObject.put("msg", "登录成功!");
            jsonObject.put("tb_user", user);
        }
        // 登录失败
        else {
            jsonObject.put("msg", "用户名或密码错误！");
        }
        return jsonObject;
    }
}
