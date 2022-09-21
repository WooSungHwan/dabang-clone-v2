package com.blackdog.dabang.domain.user;

import com.blackdog.dabang.domain.user.UserCommand.UserJoinCommand;
import com.blackdog.dabang.domain.user.agent.AgentStore;
import com.blackdog.dabang.interfaces.user.dto.UserDto.UserJoinResponse;
import com.blackdog.dabang.interfaces.user.dto.UserDto.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserStore store;
    private final UserReader reader;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public UserJoinResponse join(UserJoinCommand command) {
        // 패스워드 암호화
        String encodedPassword = passwordEncoder.encode(command.getPassword());

        // 유저 생성
        User initUser = command.toNormalUserEntity();
        initUser.setEncodedPassword(encodedPassword);

        User user = store.store(initUser);
        return new UserJoinResponse(user);
    }

    @Transactional
    @Override
    public UserJoinResponse agentJoin(UserJoinCommand command) {
        // 패스워드 암호화
        String encodedPassword = passwordEncoder.encode(command.getPassword());

        // 유저 생성
        User initUser = command.toAgentUserEntity();
        initUser.setEncodedPassword(encodedPassword);

        User user = store.store(initUser);
        return new UserJoinResponse(user);
    }

    @Override
    public UserResponse getUserBySeq(Long seq) {
        User user = reader.getUserBySeq(seq);
        return new UserResponse(user);
    }

}
