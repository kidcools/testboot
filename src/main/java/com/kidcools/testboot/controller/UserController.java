package com.kidcools.testboot.controller;

import com.kidcools.testboot.entity.User;
import com.kidcools.testboot.service.UserService;
import com.kidcools.testboot.utils.ResponseHelper;
import com.kidcools.testboot.utils.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/info")
    public @ResponseBody
    ResponseVo getUserInfo(){
        return ResponseHelper.success(new User("milestar","acrgfvbvd@#$"));
    }
    @PostMapping("/add")
    public @ResponseBody ResponseVo addUser(@RequestBody  User user){
        int i = userService.insertUser(user);
        return ResponseHelper.success(i);
    }
}
