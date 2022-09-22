package com.blackdog.dabang.domain.user;

import com.blackdog.dabang.domain.user.UserCommand.UserJoinCommand;
import com.blackdog.dabang.interfaces.user.dto.UserDto.UserJoinResponse;
import com.blackdog.dabang.interfaces.user.dto.UserDto.UserResponse;

public interface UserService {

    UserJoinResponse join(UserJoinCommand command);

    UserResponse getUserBySeq(Long userSeq);

}
