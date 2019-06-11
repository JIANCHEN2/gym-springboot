package com.jym.demo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author ：Yimyl
 * @date ：Created in 2019/5/13 16:09
 * @description：
 * @modified By：
 * @version: $
 */
public class Test {
    @org.junit.Test
    public void ttt() {
        String password = new BCryptPasswordEncoder().encode("123456");
        System.out.println(password);
    }
}
