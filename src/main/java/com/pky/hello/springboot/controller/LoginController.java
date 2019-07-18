package com.pky.hello.springboot.controller;

import com.pky.hello.springboot.commons.domain.TbUser;
import com.pky.hello.springboot.commons.dto.AbstractBaseResult;
import com.pky.hello.springboot.commons.service.LoginService;
import com.pky.hello.springboot.commons.utils.BeanValidator;
import com.pky.hello.springboot.controller.base.AbstractBaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "tb_users")
public class LoginController extends AbstractBaseController<TbUser> {

    @Autowired
    LoginService loginService;

    @GetMapping(value = "login")
    public AbstractBaseResult login(TbUser tbUser) {

        // 数据校验
        String message = BeanValidator.validator(tbUser);
        if(StringUtils.isNotBlank(message)) {
            return error(message, null);
        }
        // 登录校验
        TbUser user = loginService.getByLoginId(tbUser);
        // 登录成功
        if(user != null) {
            return success(request.getRequestURI(), user);
        }
        // 登录失败
        else {
            return error(401, "用户名或密码错误！", null);
        }
    }
}
