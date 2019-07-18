package com.pky.hello.springboot.commons.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pky.hello.springboot.commons.domain.TbUser;
import com.pky.hello.springboot.commons.mapper.TbUserMapper;
import com.pky.hello.springboot.commons.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户业务逻辑
 */
@Service
public class TbUserServiceImpl implements TbUserService {

    @Autowired
    TbUserMapper tbUserMapper;

    /**
     * 获取全部用户
     * @return
     */
    @Override
    public List<TbUser> getAll() {
        List<TbUser> tbUsers = tbUserMapper.selectAll();
        return tbUsers;
    }

    /**
     * 分页获取用户信息
     * @param pageNum 当前页数
     * @param pageSize 当前数据条数
     * @return
     */
    @Override
    public PageInfo<TbUser> page(int pageNum, int pageSize) {
        // 根据 “created” 字段倒序分页
        PageHelper.startPage(pageNum, pageSize, "created desc");

        PageInfo<TbUser> pageInfo = new PageInfo<>(tbUserMapper.selectAll());
        return pageInfo;
    }
}
