package com.vuelogix.recordingmanager.framework.security;

import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableGlobalAuthentication
public class SecurityManager extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // For the time being no security for APIs , will add it later
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/api/**").permitAll();
    }
}
