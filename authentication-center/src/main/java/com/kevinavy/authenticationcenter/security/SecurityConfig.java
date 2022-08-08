package com.kevinavy.authenticationcenter.security;

import com.kevinavy.authenticationcenter.security.filter.JwtAuthenticationTokenFilter;
import com.kevinavy.authenticationcenter.security.service.SecurityAccessDeniedHandler;
import com.kevinavy.authenticationcenter.security.service.SecurityAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Resource
    SecurityAuthenticationEntryPoint securityAuthenticationEntryPoint;

    @Resource
    SecurityAccessDeniedHandler securityAccessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //设置基本配置与放行机制
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .anyRequest().anonymous();
//                .antMatchers(
//                        "/user/register",
//                        "/user/login",
//                        "/competition/getCompetitionList/{current}/{size}/{keyword}",
//                        "/competition/getCompetitionList/{current}/{size}",
//                        "/competition/getCompetitionList/{id}",
//                        "/competition/getCompetitionDetails/{id}"
//                ).anonymous()
//                .anyRequest().authenticated();

        //增加前置jwt过滤器
        http
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        //异常处理器，用于处理认证与鉴权失败后抛出的异常
        http
                .exceptionHandling()
                .authenticationEntryPoint(securityAuthenticationEntryPoint)
                .accessDeniedHandler(securityAccessDeniedHandler);

        //允许跨域请求
        http
                .cors();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
