package com.server.config.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author ：Yimyl
 * @date ：Created in 2019/5/13 19:42
 * @description：
 * @modified By：
 * @version: $
 */
public class UserDetailServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        return null;
    }
}
