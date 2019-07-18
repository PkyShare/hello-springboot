package com.pky.hello.springboot.controller;

import com.github.pagehelper.PageInfo;
import com.pky.hello.springboot.commons.domain.TbUser;
import com.pky.hello.springboot.commons.dto.AbstractBaseResult;
import com.pky.hello.springboot.commons.service.TbUserService;
import com.pky.hello.springboot.controller.base.AbstractBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户控制器
 */
@RestController
@RequestMapping(value = "tb_users")
public class TbUserController extends AbstractBaseController<TbUser> {

    @Autowired
    TbUserService tbUserService;

    /**
     * 获取所有用户
     * @return
     */
    @GetMapping(value = "all")
    public AbstractBaseResult getAllTbUsers() {
        List<TbUser> tbUsers = tbUserService.getAll();
        // 有数据
        if(tbUsers.size() > 0) {
            return success(request.getRequestURI(),1,tbUsers.size(), tbUsers);
        }

        // 无数据
        return success(request.getRequestURI());
    }

    /**
     * 分页获取用户信息
     * @param num 当前页
     * @param size 一页总条数
     * @return
     */
    @GetMapping(value = "page/{num}/{size}")
    public AbstractBaseResult page(@PathVariable int num, @PathVariable int size) {
        PageInfo<TbUser> pageInfo = tbUserService.page(num, size);

        return success(request.getRequestURI(), pageInfo.getNextPage(), pageInfo.getPages(), pageInfo.getList());
    }
}
