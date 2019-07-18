package com.pky.hello.springboot.commons.service.impl;

import com.pky.hello.springboot.commons.domain.TbUser;
import com.pky.hello.springboot.commons.mapper.TbUserMapper;
import com.pky.hello.springboot.commons.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

/**
 * 登录业务逻辑
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    TbUserMapper tbUserMapper;

    /**
     * 通过用户名获取登录用户信息
     * @param tbUser
     * @return
     */
    @Override
    public TbUser getByLoginId(TbUser tbUser) {
        Example example = new Example(TbUser.class);
        // "username" 与实体类属性对应
        example.createCriteria().andEqualTo("username", tbUser.getUsername());
        TbUser user = tbUserMapper.selectOneByExample(example);

        boolean flag = false;
        // 查询成功
        if(user != null) {
            // 判断密码
            flag = checkPassword(user, tbUser.getPassword());
        }
        // 登录成功
        if(flag) {
            return user;
        }
        // 登录失败
        return null;
    }

    /**
     * 判断密码
     * @param tbUser
     * @param loginPwd 登录密码
     * @return
     */
    private Boolean checkPassword(TbUser tbUser, String loginPwd) {

        // 因数据库中的密码是 md5 加密，因此需要将登录密码进行加密后比较
        String password = DigestUtils.md5DigestAsHex(loginPwd.getBytes());

        // 密码正确
        if(tbUser.getPassword().equals(password)) {
            return true;
        }
        return false;
    }
}
