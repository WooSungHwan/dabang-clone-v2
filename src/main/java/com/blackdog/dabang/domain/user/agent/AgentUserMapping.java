package com.blackdog.dabang.domain.user.agent;

import com.blackdog.dabang.common.exception.InvalidParameterException;
import com.blackdog.dabang.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;

import java.util.Objects;
import java.util.UUID;

import static javax.persistence.FetchType.LAZY;

@Slf4j
@Getter
@Entity
@NoArgsConstructor
@Table(name = "agents")
public class AgentUserMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @ManyToOne(fetch = LAZY)
    private User user;

    @ManyToOne(fetch = LAZY)
    private Agent agent;

    @Builder
    public AgentUserMapping(Agent agent, User user) {
        if (Objects.isNull(agent)) throw new InvalidParameterException("AgentUserMapping.agent");
        if (Objects.isNull(user)) throw new InvalidParameterException("AgentUserMapping.user");

        this.user = user;
        this.agent = agent;
    }

}
