package com.example.service;

import com.example.dto.UserDTO;
import com.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserMapper userMapper;

    @Autowired
    private void userMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public void test() {
        UserDTO userDTO = new UserDTO();
        userMapper.insertUser(userDTO);
    }


}
