package com.xpit.vblog.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests() // 对请求授权
                .antMatchers("/swagger-ui.html").permitAll() //允许所有人访问
                .antMatchers("/v2/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/csrf").permitAll()
                .antMatchers("/").permitAll()
                .anyRequest() // 任何请求
                .authenticated()// 需要身份认证
                .and()
                .formLogin()
        ;
    }
}
