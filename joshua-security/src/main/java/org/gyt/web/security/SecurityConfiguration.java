package org.gyt.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 安全配置
 * Created by y27chen on 2016/7/12.
 */
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN_LOGIN')")
                .antMatchers("/api/**").access("hasRole('ROLE_ADMIN_LOGIN')")
                .antMatchers(
                        "/",
                        "/index",
                        "/home",
                        "/bible",
                        "/believe",
                        "/contact",
                        "/financial",
                        "/group",
                        "/about",
                        "/wedding",
                        "/media",
                        "/newcomer",
                        "/pastor",
                        "/preach",
                        "/recommend",
                        "/report",
                        "/sunday",
                        "/testimony",
                        "/worship",
                        "/login",
                        "/logon",
                        "/assets/**",
                        "/images/**"
                ).permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error")
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/403");
    }
}
