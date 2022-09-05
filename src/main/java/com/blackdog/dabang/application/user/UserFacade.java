package com.blackdog.dabang.application.user;

import com.blackdog.dabang.domain.user.UserCommand.UserJoinCommand;
import com.blackdog.dabang.domain.user.UserService;
import com.blackdog.dabang.interfaces.user.dto.UserDto.UserJoinResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserFacade {

    private final UserService service;

    public UserJoinResponse join(UserJoinCommand command) {
        return service.join(command);
    }

}
