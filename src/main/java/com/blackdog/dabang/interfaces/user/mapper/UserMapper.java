package com.blackdog.dabang.interfaces.user.mapper;

import com.blackdog.dabang.domain.user.agent.AgentCommand.AgentAddCommand;
import com.blackdog.dabang.domain.user.UserCommand.UserJoinCommand;
import com.blackdog.dabang.interfaces.user.dto.AgentDto.AgentAddRequest;
import com.blackdog.dabang.interfaces.user.dto.UserDto.UserJoinRequest;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface UserMapper {

    UserJoinCommand toUserJoinCommand(UserJoinRequest userJoinRequest);

    AgentAddCommand toAgentAddCommand(AgentAddRequest agentAddRequest);

}
