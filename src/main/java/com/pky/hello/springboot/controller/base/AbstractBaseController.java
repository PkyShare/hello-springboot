package com.pky.hello.springboot.controller.base;

import com.pky.hello.springboot.commons.dto.AbstractBaseDomain;
import com.pky.hello.springboot.commons.dto.AbstractBaseResult;
import com.pky.hello.springboot.commons.dto.BaseResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 统一的 Controller
 * @param <T>
 */
@CrossOrigin //解决跨域
public class AbstractBaseController<T extends AbstractBaseDomain> {

    // 用于动态获取配置文件的属性值
    private static final String LOGGER_LEVEL_PETCLINIC = "logging.level.com.huanda.illegalquerypc";

    @Resource
    protected HttpServletRequest request;
    @Resource
    protected HttpServletResponse response;

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @ModelAttribute  //在所有的 @RequestMapping 前执行
    public void initReqAndRes(HttpServletRequest request, HttpServletResponse response){
        this.request = request;
        this.response = response;
    }

    /**
     * 删除成功响应结果1
     * @param self
     * @return
     */
    protected AbstractBaseResult success(String self){
        return BaseResultFactory.getInstance(response).build(self);
    }

    /**
     * 一条成功响应结果
     * @param self
     * @param attribute
     * @return
     */
    public AbstractBaseResult success(String self, T attribute){
        return BaseResultFactory.getInstance(response).build(self, attribute);
    }


    /**
     * 多条成功响应结果
     * @param self
     * @param next
     * @param last
     * @param attributes
     * @return
     */
    protected AbstractBaseResult success(String self, int next, int last, List<T> attributes){
        return BaseResultFactory.getInstance(response).build(self, next, last, attributes);
    }

    /**
     * 失败响应结果，为了降低响应状态码的重复率
     * @param title
     * @param detail
     * @return
     */
    protected AbstractBaseResult error(String title, String detail){
        return error(HttpStatus.UNAUTHORIZED.value(), title, detail);
    }

    /**
     * 失败响应结果
     * @param code
     * @param title
     * @param detail
     * @return
     */
    protected AbstractBaseResult error(int code, String title, String detail){
        return BaseResultFactory.getInstance(response).build(code, title, detail, applicationContext.getEnvironment().getProperty(LOGGER_LEVEL_PETCLINIC));
    }
}
