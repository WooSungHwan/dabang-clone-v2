package com.blackdog.dabang.infrastructures.user.agent;

import com.blackdog.dabang.domain.user.agent.AgentUserMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentUserMappingRepository extends JpaRepository<AgentUserMapping, Long> {
}
