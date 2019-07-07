package com.pky.hello.springboot.controller;

import com.pky.hello.springboot.HelloSpringbootApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HelloSpringbootApplication.class)
public class LoginControllerTest {

    @Autowired
    LoginController loginController;

}
