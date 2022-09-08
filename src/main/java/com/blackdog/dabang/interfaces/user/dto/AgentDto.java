package com.blackdog.dabang.interfaces.user.dto;

import com.blackdog.dabang.domain.user.agent.Agent;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AgentDto {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class AgentAddRequest {
        @NotBlank(message = "부동산 상호명을 입력해주세요.")
        private String agentName;

        @NotBlank(message = "부동산 사업자 번호를 입력해주세요.")
        private String businessId;

        @NotBlank(message = "부동산 전화번호를 입력해주세요.")
        private String tel;
    }

    @Getter
    public static class AgentAddResponse {
        private String id;

        private String name;

        private String tel;

        public AgentAddResponse(Agent agent) {
            this.id = agent.getId();
            this.name = agent.getName();
            this.tel = agent.getTel();
        }

    }

}
