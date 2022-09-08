package com.blackdog.dabang.domain.user.agent;

import com.blackdog.dabang.domain.user.agent.AgentCommand.AgentAddCommand;
import com.blackdog.dabang.interfaces.user.dto.AgentDto.AgentAddResponse;

public interface AgentService {

    AgentAddResponse addAgent(long usersSeq, AgentAddCommand command);

}
