package com.kevinavy.authenticationcenter.service.impl;

import com.kevinavy.authenticationcenter.model.common.Response;
import com.kevinavy.authenticationcenter.model.vo.UserVo;
import com.kevinavy.authenticationcenter.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public Response register(UserVo user) {
        String username = user.getUsername();
        String password = passwordEncoder.encode(user.getPassword());
        return null;
    }

    @Override
    public Response login(UserVo user) {
        return null;
    }

    @Override
    public Response logout() {
        return null;
    }

    @Override
    public Response deleteUser(UserVo user) {
        return null;
    }

    @Override
    public Response updateUser(UserVo user) {
        return null;
    }
}
