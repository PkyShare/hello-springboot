package com.pky.hello.springboot.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 通用错误返回结果
 */
@Data
@AllArgsConstructor   //创建有参的构造函数
public class ErrorResult extends AbstractBaseResult {

    private int code;
    private String title;
    private String detail;
}