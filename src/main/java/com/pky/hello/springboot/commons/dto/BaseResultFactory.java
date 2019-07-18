package com.pky.hello.springboot.commons.dto;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 统一相应结果工厂
 * @param <T>
 */
public class BaseResultFactory<T extends AbstractBaseDomain> {

    private static final String LOGGER_LEVEL_DEBUG = "DEBUG";

    private static BaseResultFactory baseResultFactory;

    private BaseResultFactory() {

    }

    private static HttpServletResponse response;

    /**
     * 单例模式获取 BaseResultFactory 实例
     * @param response
     * @return
     */
    public static BaseResultFactory getInstance(HttpServletResponse response) {
        if(baseResultFactory == null) {
            synchronized (BaseResultFactory.class) {
                if(baseResultFactory == null) {
                    baseResultFactory = new BaseResultFactory();
                }
            }
        }
        BaseResultFactory.response = response;
        baseResultFactory.initResponse();
        return  baseResultFactory;
    }

    /**
     * 删除工厂
     * @param self
     * @return
     */
    public AbstractBaseResult build(String self){
        return new SuccessResult(self);
    }

    /**
     * 单笔数据工厂
     * @param self
     * @return
     */
    public AbstractBaseResult build(String self, T attributes) {
        return new SuccessResult(self, attributes);
    }

    /**
     * 多笔数据工厂
     * @param self
     * @param next
     * @param last
     * @return
     */
    public AbstractBaseResult build(String self, int next, int last, List<T> attributes) {
        return new SuccessResult(self, next, last, attributes);
    }

    /**
     * 错误信息工厂
     * @param code
     * @param title
     * @param detail（开发环境可见，生产环境屏蔽）
     * @return
     */
    public static AbstractBaseResult build(int code, String title, String detail, String level) {
        //设置请求失败的响应码
        response.setStatus(code);

        if(LOGGER_LEVEL_DEBUG.equals(level)) {
            return new ErrorResult(code, title, detail);
        } else {
            return new ErrorResult(code, title, null);
        }

    }

    /**
     * 设置响应头
     */
    private void initResponse(){
        response.setHeader("Content-Type", "application/vnd.api+json");
    }

}