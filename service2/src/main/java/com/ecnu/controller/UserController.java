package com.ecnu.controller;

import com.ecnu.model.User;
import com.ecnu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Created by kun on 17-6-27.
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    private  static final int SUCCESS_STATUS = 200;
    private static  final  int FAIL_STATUS = -1;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public int login(@RequestBody User user){
        System.out.println("login in");
        int rows =  userService.login(user);
        System.out.println("login finish");
        System.out.println(rows);
        return rows>0?SUCCESS_STATUS:FAIL_STATUS;
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public int register(@RequestBody User user){
        System.out.println(user.getLoginId());
        int rows=userService.register(user);
        return rows>0? SUCCESS_STATUS:FAIL_STATUS;
    }


}
