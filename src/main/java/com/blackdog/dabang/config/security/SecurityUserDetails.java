package com.blackdog.dabang.config.security;

import com.blackdog.dabang.domain.user.User;
import com.blackdog.dabang.domain.user.User.UserType;
import java.util.Collection;
import java.util.List;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Slf4j
public class SecurityUserDetails implements UserDetails {

    private Long seq;

    private String name;

    private String id;

    private String password;

    private UserType type;

    public SecurityUserDetails(User user) {
        this.seq = user.getSeq();
        this.name = user.getName();
        this.id = user.getUserId();
        this.password = user.getPassword();
        this.type = user.getType();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        switch (this.type) {
            case NORMAL -> {
                return List.of(new SimpleGrantedAuthority(Role.ROLE_NORMAL));
            }
            case AGENT -> List.of(new SimpleGrantedAuthority(Role.ROLE_AGENT));
        }
        log.error("해당 유저의 상태를 확인해주세요. {}", id);
        throw new IllegalStateException("해당 유저의 상태를 확인해주세요.");
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
