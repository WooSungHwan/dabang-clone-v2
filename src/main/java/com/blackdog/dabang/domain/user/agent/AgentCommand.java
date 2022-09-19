package com.blackdog.dabang.domain.user.agent;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class AgentCommand {

    @Getter
    @Builder
    @ToString
    public static class AgentAddCommand {
        private String agentName;
        private String businessId;
        private String tel;

        public Agent toEntity() {
            return Agent.builder()
                    .name(agentName)
                    .businessId(businessId)
                    .tel(tel)
                    .build();
        }
    }

}
