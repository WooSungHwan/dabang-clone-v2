package com.blackdog.dabang.application.user;

import com.blackdog.dabang.domain.user.agent.AgentCommand.AgentAddCommand;
import com.blackdog.dabang.domain.user.UserCommand.UserJoinCommand;
import com.blackdog.dabang.domain.user.UserService;
import com.blackdog.dabang.domain.user.agent.AgentService;
import com.blackdog.dabang.interfaces.user.dto.AgentDto.AgentAddResponse;
import com.blackdog.dabang.interfaces.user.dto.UserDto.UserAgentJoinResponse;
import com.blackdog.dabang.interfaces.user.dto.UserDto.UserJoinResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserFacade {

    private final UserService service;
    private final AgentService agentService;

    public UserJoinResponse join(UserJoinCommand command) {
        return service.join(command);
    }

    public UserAgentJoinResponse agentJoin(UserJoinCommand userJoinCommand, AgentAddCommand agentAddCommand) {
        UserJoinResponse user = service.join(userJoinCommand);
        AgentAddResponse agent = agentService.addAgent(user.getSeq(), agentAddCommand);
        return UserAgentJoinResponse.of(user, agent);
    }

}