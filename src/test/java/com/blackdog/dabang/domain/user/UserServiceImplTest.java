package com.blackdog.dabang.domain.user;

import static com.blackdog.dabang.domain.user.User.UserType.AGENT;
import static com.blackdog.dabang.domain.user.User.UserType.NORMAL;
import static org.assertj.core.api.Assertions.assertThat;

import com.blackdog.dabang.domain.user.UserCommand.UserJoinCommand;
import com.blackdog.dabang.interfaces.user.dto.UserDto.UserJoinResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

//@ExtendWith(MockitoExtension.class)
@Transactional
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService service;

    @Autowired
    private UserReader reader;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void join() {
        UserJoinCommand command = UserJoinCommand.builder()
                                               .name("흑구")
                                               .id("test")
                                               .password("test")
                                               .build();

        UserJoinResponse response = service.join(command);

        assertThat(response).extracting("seq").isNotNull();
        assertThat(response).extracting("name").isEqualTo(command.getName());
        assertThat(response).extracting("id").isEqualTo(command.getId());
        assertThat(response).extracting("type").isEqualTo(NORMAL);

        User user = reader.getUserBySeq(response.getSeq());
        boolean matches = passwordEncoder.matches(
                command.getPassword(),
                user.getPassword()
        );
        assertThat(matches).isTrue();
    }

    @Test
    void agentJoin() {
        UserJoinCommand command = UserJoinCommand.builder()
                .name("흑구 부동산 유저")
                .id("blackdog")
                .password("blackdog")
                .build();
        UserJoinResponse response = service.agentJoin(command);

        assertThat(response).extracting("seq").isNotNull();
        assertThat(response).extracting("name").isEqualTo(command.getName());
        assertThat(response).extracting("id").isEqualTo(command.getId());
        assertThat(response).extracting("type").isEqualTo(AGENT);

        User user = reader.getUserBySeq(response.getSeq());
        boolean matches = passwordEncoder.matches(
                command.getPassword(),
                user.getPassword()
        );
        assertThat(matches).isTrue();
    }

}