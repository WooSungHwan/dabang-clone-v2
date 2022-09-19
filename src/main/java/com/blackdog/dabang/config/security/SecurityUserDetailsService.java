package com.blackdog.dabang.config.security;

import com.blackdog.dabang.domain.user.User;
import com.blackdog.dabang.domain.user.UserReader;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityUserDetailsService implements UserDetailsService {

    private final UserReader userReader;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userReader.getUserByUserId(userId);
        return new SecurityUserDetails(user);
    }
}
