package com.example.web.controller;

import com.example.web.exception.ParameterException;
import com.example.web.model.User;
import com.example.web.service.FooService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * @author Lei
 * craeted: 2019/10/19
 */
@RestController
@RequestMapping("/foo")
@Slf4j
public class FooController {

    private FooService fooService;

    public FooController(FooService fooService) {
        this.fooService = fooService;
    }

    @GetMapping("/void")
    public void aVoid() {
        // 无需返回数据，此处编写业务代码
    }

    @GetMapping("/string")
    public String string() {
        return "Hello World!";
    }

    @GetMapping("/int")
    public Integer aInt() {
        return 123456;
    }

    @GetMapping("/object")
    public User object() {
        return new User("Tom", 22);
    }

    @GetMapping("/list")
    public List<User> list() {
        return Arrays.asList(new User("Tom", 22), new User("Lucy", 18));
    }

    @GetMapping("/service")
    public User service() {
        log.info("controller ....");
        return fooService.getUserOrException();
    }

    @GetMapping("/business_exception")
    public String businessException(String flag) {
        if (flag == null) {
            // 参数验证不通过
            throw new ParameterException();
        }

        return flag;
    }

    @GetMapping("/exception")
    public String exception(String flag) {
        if (flag == null) {
            // 演示：抛出一个运行时异常
            throw new RuntimeException("flag must not be null");
        }

        return flag;
    }

    @PostMapping("/param")
    public void param(HttpServletRequest request) {
        System.out.println(request.getParameter("token"));

    }
}
