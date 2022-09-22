package com.blackdog.dabang.interfaces.user.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.blackdog.dabang.TestConfig;
import com.blackdog.dabang.domain.user.UserStore;
import com.blackdog.dabang.interfaces.user.dto.UserDto.UserLoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest(classes = TestConfig.class)
class LoginControllerTest {

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    private static final String BASE_URL = "/api/v1/login";


    @Test
    void 로그인() throws Exception {
        UserLoginRequest request = new UserLoginRequest("test", "test_password");

        mockMvc.perform(post(BASE_URL)
                       .contentType(MediaType.APPLICATION_JSON_VALUE)
                       .content(objectMapper.writeValueAsString(request)))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.result.token").exists());
    }

}