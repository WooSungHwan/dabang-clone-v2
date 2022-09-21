package com.blackdog.dabang.infrastructures.user.agent;

import com.blackdog.dabang.common.exception.EntityNotFoundException;
import com.blackdog.dabang.domain.user.User;
import com.blackdog.dabang.domain.user.agent.Agent;
import com.blackdog.dabang.domain.user.agent.AgentReader;
import com.blackdog.dabang.domain.user.agent.AgentUserMapping;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AgentReaderImpl implements AgentReader {

    private final AgentRepository repository;
    private final AgentUserMappingRepository agentUserMappingRepository;

    @Override
    public Agent getAgentByUser(User user) {
        AgentUserMapping agentUserMapping = agentUserMappingRepository.findByUser(user)
                                                                      .orElseThrow(EntityNotFoundException::new);
        return agentUserMapping.getAgent();
    }

    @Override
    public Agent getAgentById(String id) {
        return repository.findByAgentId(id)
                  .orElseThrow(EntityNotFoundException::new);
    }

}
