package com.blackdog.dabang.infrastructures.user.agent;

import com.blackdog.dabang.domain.user.agent.Agent;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepository extends JpaRepository<Agent, Long> {

    Optional<Agent> findByAgentId(String id);

}
