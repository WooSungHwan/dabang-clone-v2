package com.blackdog.dabang.domain.user.agent;

import com.blackdog.dabang.domain.user.User;
import com.blackdog.dabang.domain.user.agent.AgentCommand.AgentAddCommand;

public interface AgentStore {

    Agent addAgent(Agent agent);

    AgentUserMapping mappingAgentWithUser(AgentUserMapping mapping);

}
