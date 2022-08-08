package com.kevinavy.authenticationcenter.controller;


import com.kevinavy.authenticationcenter.model.common.Response;
import com.kevinavy.authenticationcenter.model.vo.UserVo;
import com.kevinavy.authenticationcenter.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @PostMapping("/register")
    public Response register(@RequestBody UserVo user) {
        return userService.register(user);
    }

//    @PostMapping("/login")
//    public Response login(@RequestBody UserVo user) {
//        return userService.login(user);
//    }
//
//    @PostMapping("/logout")
//    public Response login() {
//        return userService.logout();
//    }
//
//    @PostMapping("/delete")
//    @PreAuthorize("hasAuthority('sys:user:delete')")
//    public Response deleteUser(@RequestBody UserVo user) {
//        return userService.deleteUser(user);
//    }
//
//    @PostMapping("/update")
//    @PreAuthorize("hasAuthority('sys:user:update')")
//    public Response updateUser(@RequestBody UserVo user) {
//        return userService.updateUser(user);
//    }

}
