package com.blackdog.dabang.domain.user.agent;

import com.blackdog.dabang.common.exception.InvalidParameterException;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
@Getter
@Entity
@NoArgsConstructor
@Table(name = "agents")
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "agent_id", length = 50, unique = true)
    private String agentId; // 별도의 대체 unique 키

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "business_id", length = 50)
    private String businessId;

    @Column(name = "tel", length = 50)
    private String tel;

    @Builder
    public Agent(String name, String businessId, String tel) {
        if (StringUtils.isBlank(name)) throw new InvalidParameterException("Agent.name");
        if (StringUtils.isBlank(businessId)) throw new InvalidParameterException("Agent.businessId");
        if (StringUtils.isBlank(tel)) throw new InvalidParameterException("Agent.tel");

        this.name = name;
        this.businessId = businessId;
        this.tel = tel;
        this.agentId = UUID.randomUUID().toString(); // 대체 유니크키 생성
    }
}
