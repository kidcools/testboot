package com.kidcools.testboot.service.impl;

import com.kidcools.testboot.entity.User;
import com.kidcools.testboot.mapper.UserMapper;
import com.kidcools.testboot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public int insertUser(User user) {
        log.info("userInfo:{}",user);
        return userMapper.addUser(user);
    }
}
