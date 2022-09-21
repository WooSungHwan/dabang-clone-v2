package com.blackdog.dabang.domain.user.agent;

import com.blackdog.dabang.domain.user.agent.AgentCommand.AgentAddCommand;
import com.blackdog.dabang.interfaces.user.dto.AgentDto.AgentAddResponse;
import com.blackdog.dabang.interfaces.user.dto.AgentDto.AgentResponse;

public interface AgentService {

    AgentAddResponse addAgent(long usersSeq, AgentAddCommand command);

    AgentResponse getAgentByUserSeq(long userSeq);

}
