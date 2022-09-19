package com.blackdog.dabang.interfaces.user.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.blackdog.dabang.domain.user.User;
import com.blackdog.dabang.domain.user.UserCommand.UserJoinCommand;
import com.blackdog.dabang.domain.user.UserStore;
import com.blackdog.dabang.interfaces.user.dto.UserDto.UserLoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
class LoginControllerTest {

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    private static final String BASE_URL = "/api/v1/login";

    @Autowired
    UserStore userStore;

    @Autowired
    PasswordEncoder passwordEncoder;

    final String TARGET_ID = "blackdog";
    final String TARGET_PASSWORD = "blackdogPassword";

    @BeforeEach
    void setUp() {
        UserJoinCommand command = UserJoinCommand.builder()
                                               .name("흑구")
                                               .id(TARGET_ID)
                                               .password(TARGET_PASSWORD)
                                               .build();

        User initUser = command.toNormalUserEntity();
        initUser.setEncodedPassword(passwordEncoder.encode(command.getPassword()));

        userStore.store(initUser);
    }

    @Test
    void 로그인() throws Exception {
        UserLoginRequest request = new UserLoginRequest(TARGET_ID, TARGET_PASSWORD);

        mockMvc.perform(post(BASE_URL)
                       .contentType(MediaType.APPLICATION_JSON_VALUE)
                       .content(objectMapper.writeValueAsString(request)))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.result.token").exists());
    }

}