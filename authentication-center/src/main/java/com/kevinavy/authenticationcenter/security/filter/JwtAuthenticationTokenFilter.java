package com.kevinavy.authenticationcenter.security.filter;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kevinavy.authenticationcenter.security.JsonWebTokenCenter;
import com.kevinavy.authenticationcenter.security.mapper.SecurityUserMapper;
import com.kevinavy.authenticationcenter.security.model.Permission;
import com.kevinavy.authenticationcenter.security.model.SecurityUserDetails;
import com.kevinavy.authenticationcenter.security.model.User;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Resource
    SecurityUserMapper securityUserMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        String username = JsonWebTokenCenter.getUsernameByToken(token);
        if (StringUtils.isEmpty(username)) {
            throw  new RuntimeException("用户信息非法");
        }

        // todo 从redis获取用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        User user = securityUserMapper.selectOne(queryWrapper);
        if (ObjectUtils.isEmpty(user)) {
            throw new RuntimeException("用户未登录");
        }
        List<Permission> permissions = securityUserMapper.findPermissionsByUsername(username);
        SecurityUserDetails securityUserDetails = new SecurityUserDetails(user, permissions);

        // todo 获取权限信息并写入
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(securityUserDetails, null, securityUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        filterChain.doFilter(request, response);

    }
}
