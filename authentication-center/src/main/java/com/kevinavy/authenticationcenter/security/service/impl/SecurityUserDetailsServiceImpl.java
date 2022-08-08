package com.kevinavy.authenticationcenter.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kevinavy.authenticationcenter.security.mapper.SecurityUserMapper;
import com.kevinavy.authenticationcenter.security.model.Permission;
import com.kevinavy.authenticationcenter.security.model.SecurityUserDetails;
import com.kevinavy.authenticationcenter.security.model.User;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SecurityUserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private SecurityUserMapper securityUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        User user = securityUserMapper.selectOne(queryWrapper);
        if (ObjectUtils.isEmpty(user)) {
            throw new RuntimeException("用户名或密码错误");
        }

        List<Permission> permissions = securityUserMapper.findPermissionsByUsername(username);


        return new SecurityUserDetails(user, permissions);
    }
}
