package com.blackdog.dabang.domain.user.agent;

import com.blackdog.dabang.domain.user.User;

public interface AgentReader {

    Agent getAgentByUser(User user);

    Agent getAgentById(String id);
}
