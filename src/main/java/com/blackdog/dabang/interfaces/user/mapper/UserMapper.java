package com.blackdog.dabang.interfaces.user.mapper;

import com.blackdog.dabang.domain.user.UserCommand.UserJoinCommand;
import com.blackdog.dabang.interfaces.user.dto.UserDto.UserJoinRequest;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface UserMapper {

    UserJoinCommand toUserJoinCommand(UserJoinRequest userJoinRequest);

}
