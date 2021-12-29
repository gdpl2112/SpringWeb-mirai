
package io.github.kloping.springwebmirai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author github-kloping
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    PasswordEncoder passwordEncoder;

    public static final String ADMIN = "admin";
    public static final String NOT_FOUND_USER = "用户名不存在";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!ADMIN.equals(username)) throw new UsernameNotFoundException(NOT_FOUND_USER);
        String password = passwordEncoder.encode("password");
        return new User(username, password,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}