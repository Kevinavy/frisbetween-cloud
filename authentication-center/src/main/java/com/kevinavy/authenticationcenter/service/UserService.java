package com.kevinavy.authenticationcenter.service;

import com.kevinavy.authenticationcenter.model.common.Response;
import com.kevinavy.authenticationcenter.model.vo.UserVo;


public interface UserService {
    Response register(UserVo user);

    Response login(UserVo user);

    Response logout();

    Response deleteUser(UserVo user);

    Response updateUser(UserVo user);
}
