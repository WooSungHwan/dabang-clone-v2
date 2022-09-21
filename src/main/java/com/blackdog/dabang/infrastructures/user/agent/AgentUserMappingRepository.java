package com.blackdog.dabang.infrastructures.user.agent;

import com.blackdog.dabang.domain.user.User;
import com.blackdog.dabang.domain.user.agent.AgentUserMapping;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentUserMappingRepository extends JpaRepository<AgentUserMapping, Long> {

    Optional<AgentUserMapping> findByUser(User user);

}
