package com.pky.hello.springboot.commons.service;

import com.pky.hello.springboot.commons.domain.TbUser;

public interface LoginService {

    /**
     * 通过用户名获取登录用户信息
     * @param tbUser
     * @return
     */
    TbUser getByLoginId(TbUser tbUser);
}
