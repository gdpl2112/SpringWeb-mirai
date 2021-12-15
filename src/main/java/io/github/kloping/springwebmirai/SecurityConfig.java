package io.github.kloping.springwebmirai;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 配置认证
        http.formLogin()
                // 哪个URL为登录页面
                .loginPage("/login.html")
                // 当发现什么URL时执行登录逻辑
                .loginProcessingUrl("/login")
                // 成功后跳转到哪里
                .successForwardUrl("/success")
                // 失败后跳转到哪里
                .failureForwardUrl("/fail");

        // 设置URL的授权问题
        // 多个条件取交集
        http.authorizeRequests()
                // 匹配 / 控制器  permitAll() 不需要被认证就可以访问
                .antMatchers("/login.html").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/index").permitAll()
                // anyRequest() 所有请求   authenticated() 必须被认证
                .mvcMatchers("/manager").authenticated()
                .anyRequest().authenticated();

        // 关闭csrf
        http.csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}