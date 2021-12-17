package io.github.kloping.springwebmirai;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final Set<String> NotNeedAuthPages = new CopyOnWriteArraySet<>();

    static {
        NotNeedAuthPages.add("/login.html");
        NotNeedAuthPages.add("/index.html");
        NotNeedAuthPages.add("/js/*");
        NotNeedAuthPages.add("/css/*");
        NotNeedAuthPages.add("/index");
        NotNeedAuthPages.add("/getBots");
        NotNeedAuthPages.add("/plugins");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .failureForwardUrl("/fail")
                .and()
                .authorizeRequests()
                .antMatchers(NotNeedAuthPages.toArray(new String[0])).permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();// 使用不使用加密算法保持密码
//        return new BCryptPasswordEncoder();
    }
}