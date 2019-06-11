package com.jym.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void contextLoads() {
        String password = new BCryptPasswordEncoder().encode("123456");
        System.out.println(password);
    }

    @Test
    public void test() {
        String password = new BCryptPasswordEncoder().encode("123456");
        System.out.println(password);
    }
}
