package io.github.kloping.springwebmirai;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author github-kloping
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final Set<String> NOT_NEED_AUTH_PAGES = new CopyOnWriteArraySet<>();

    static {
        NOT_NEED_AUTH_PAGES.add("/login.html");
        NOT_NEED_AUTH_PAGES.add("/index.html");
        NOT_NEED_AUTH_PAGES.add("/js/*");
        NOT_NEED_AUTH_PAGES.add("/css/*");
        NOT_NEED_AUTH_PAGES.add("/index");
        NOT_NEED_AUTH_PAGES.add("/getBots");
        NOT_NEED_AUTH_PAGES.add("/plugins");
//        NOT_NEED_AUTH_PAGES.add("/manager/terminal.html");
//        NOT_NEED_AUTH_PAGES.add("/server_terminal");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .failureForwardUrl("/fail")
                .and()
                .authorizeRequests()
                .antMatchers(NOT_NEED_AUTH_PAGES.toArray(new String[0])).permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
//        return new BCryptPasswordEncoder();
    }
}