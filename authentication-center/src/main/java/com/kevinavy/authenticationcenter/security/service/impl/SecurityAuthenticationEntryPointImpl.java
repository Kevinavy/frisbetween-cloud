package com.kevinavy.authenticationcenter.security.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.kevinavy.authenticationcenter.constant.enums.ResponseCode;
import com.kevinavy.authenticationcenter.model.common.Response;
import com.kevinavy.authenticationcenter.security.service.SecurityAuthenticationEntryPoint;
import com.kevinavy.authenticationcenter.utils.WebUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SecurityAuthenticationEntryPointImpl implements SecurityAuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String message = authException.getMessage();
        if (StringUtils.isEmpty(message)) {
            WebUtils.renderString(response, JSONObject.toJSONString(Response.error(ResponseCode.UNAUTHORIZED)));
        }
        else {
            WebUtils.renderString(response, JSONObject.toJSONString(Response.error(ResponseCode.UNAUTHORIZED, message)));
        }
    }
}
