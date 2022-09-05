package com.blackdog.dabang.infrastructures.user;

import com.blackdog.dabang.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
