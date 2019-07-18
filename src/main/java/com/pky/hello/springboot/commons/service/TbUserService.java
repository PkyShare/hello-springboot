package com.pky.hello.springboot.commons.service;

import com.github.pagehelper.PageInfo;
import com.pky.hello.springboot.commons.domain.TbUser;

import java.util.List;

public interface TbUserService {

    /**
     * 获取全部用户
     * @return
     */
    List<TbUser> getAll();

    /**
     * 分页获取用户信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<TbUser> page(int pageNum, int pageSize);
}
