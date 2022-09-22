package com.blackdog.dabang.infrastructures.user;

import com.blackdog.dabang.common.exception.EntityNotFoundException;
import com.blackdog.dabang.domain.user.User;
import com.blackdog.dabang.domain.user.UserReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserReaderImpl implements UserReader {

    private final UserRepository repository;

    @Override
    public User getUserBySeq(Long seq) {
        return repository.findById(seq)
                         .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public User getUserByUserId(String userId) {
        return repository.findByUserId(userId)
                         .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public boolean existsUserId(String userId) {
        return repository.existsByUserId(userId);
    }

}
