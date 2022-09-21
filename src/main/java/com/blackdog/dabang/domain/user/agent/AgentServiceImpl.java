package com.blackdog.dabang.domain.user.agent;

import com.blackdog.dabang.domain.user.User;
import com.blackdog.dabang.domain.user.UserReader;
import com.blackdog.dabang.domain.user.agent.AgentCommand.AgentAddCommand;
import com.blackdog.dabang.interfaces.user.dto.AgentDto.AgentAddResponse;
import com.blackdog.dabang.interfaces.user.dto.AgentDto.AgentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class AgentServiceImpl implements AgentService {

    private final AgentStore store;
    private final AgentReader reader;
    private final UserReader userReader;

    @Transactional
    @Override
    public AgentAddResponse addAgent(long usersSeq, AgentAddCommand command) {
        // 유저 조회
        User user = userReader.getUserBySeq(usersSeq);

        // 부동산 등록
        Agent agent = store.addAgent(command.toEntity());

        // 부동산 유저 매핑
        AgentUserMapping mapping = AgentUserMapping.builder()
                .user(user)
                .agent(agent)
                .build();
        store.mappingAgentWithUser(mapping);

        return new AgentAddResponse(agent);
    }

    @Override
    public AgentResponse getAgentByUserSeq(long userSeq) {
        User user = userReader.getUserBySeq(userSeq);
        Agent agent = reader.getAgentByUser(user);
        return new AgentResponse(agent);
    }

}
