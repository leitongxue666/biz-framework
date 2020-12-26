package com.example.web;

import com.example.web.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author Lei
 * craeted: 2020/4/15
 */
@SpringBootTest
public class RestTemplateTest {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void test() {
//        String json = RequestParamBuilder.builder()
//                .add("username", "tom")
//                .add("age", 12)
//                .buildJsonString();
////
//        User user = new User();
//        user.setUsername("tom");
//        user.setAge(12);
//
////        restTemplate.postForEntity("/test/post", user, null);
//
////        Map map = restTemplate.postForObject("/test/post", user, Map.class);
//        ResponseEntity<Void> responseEntity = restTemplate.postForEntity("/test/post", user, Void.class);
//        System.out.println(responseEntity.getStatusCode());
//        System.out.println(responseEntity.getBody());
//        System.out.println(responseEntity);
////        System.out.println(map);
//
//
//        URI uri = UriComponentsBuilder.fromUriString("http://localhost/test/get?name={name}&age={age}")
//                .build()
//                .expand("tom", 123)
//                .encode()
//                .toUri();
//
//        System.out.println(uri);

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        for (int i = 0; i < 100; i++) {
            int index = new Random().nextInt(list.size());
            System.out.println("index = " + index);
            list.get(index);
        }

    }


}
