package com.fymod.shiro.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fymod.shiro.dao.UserRepository;
import com.fymod.shiro.domain.User;
import com.fymod.shiro.service.ILoginService;

@Service
@Transactional
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private UserRepository userRepository;
    
    //查询用户通过用户名
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
