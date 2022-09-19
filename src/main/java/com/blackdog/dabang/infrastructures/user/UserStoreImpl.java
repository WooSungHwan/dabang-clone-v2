package com.blackdog.dabang.infrastructures.user;

import com.blackdog.dabang.common.exception.InvalidParameterException;
import com.blackdog.dabang.domain.user.User;
import com.blackdog.dabang.domain.user.UserStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserStoreImpl implements UserStore {

    private final UserRepository repository;

    @Override
    public User store(User user) {
        validateForStore(user);
        return repository.save(user);
    }

    private void validateForStore(User user) {
        if (StringUtils.isBlank(user.getName())) throw new InvalidParameterException("user.name");
        if (StringUtils.isBlank(user.getUserId())) throw new InvalidParameterException("user.id");
        if (StringUtils.isBlank(user.getPassword())) throw new InvalidParameterException("user.password");
    }

}
