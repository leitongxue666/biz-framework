package com.example.web.service.impl;

import com.example.web.exception.DataNotFoundException;
import com.example.web.model.User;
import com.example.web.service.FooService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author Lei
 * craeted: 2019/10/19
 */
@Component
@Slf4j
public class FooServiceImpl implements FooService {

    @Override
    public User getUserOrException() {
        log.info("service ...");
        int i = new Random().nextInt(100);
        if (i % 2 == 0) {
            return new User("Tom", i);
        }

        log.error("data not found ...");
        throw new DataNotFoundException();
    }
}
