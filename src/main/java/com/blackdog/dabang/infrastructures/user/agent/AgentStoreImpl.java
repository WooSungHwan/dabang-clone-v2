package com.blackdog.dabang.infrastructures.user.agent;

import com.blackdog.dabang.common.exception.InvalidParameterException;
import com.blackdog.dabang.domain.user.User;
import com.blackdog.dabang.domain.user.agent.Agent;
import com.blackdog.dabang.domain.user.agent.AgentStore;
import com.blackdog.dabang.domain.user.agent.AgentUserMapping;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AgentStoreImpl implements AgentStore {

    private final AgentRepository repository;
    private final AgentUserMappingRepository agentUserMappingRepository;

    @Override
    public Agent addAgent(Agent agent) {
        validateForStore(agent);
        return repository.save(agent);
    }

    @Override
    public AgentUserMapping mappingAgentWithUser(AgentUserMapping mapping) {
        return agentUserMappingRepository.save(mapping);
    }

    private void validateForStore(Agent agent) {
        if (StringUtils.isBlank(agent.getName())) throw new InvalidParameterException("agent.name");
        if (StringUtils.isBlank(agent.getBusinessId())) throw new InvalidParameterException("agent.businessId");
        if (StringUtils.isBlank(agent.getTel())) throw new InvalidParameterException("agent.tel");
    }
}
