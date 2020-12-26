package com.example.web.controller;

import com.example.web.model.User;
import org.springframework.web.bind.annotation.*;

/**
 * @author Lei
 * craeted: 2020/4/15
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("/post")
    public void post(@RequestBody User user) {
        System.out.println("TestController.post");
        System.out.println(user);
    }

    @GetMapping("/get")
    public void get(@RequestParam String name, @RequestParam Integer age) {
        System.out.println("TestController.get");
        System.out.println("name = " + name);
        System.out.println("age = " + age);
    }
}
