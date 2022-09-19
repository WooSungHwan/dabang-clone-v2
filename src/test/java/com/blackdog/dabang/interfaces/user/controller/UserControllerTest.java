package com.blackdog.dabang.interfaces.user.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.blackdog.dabang.interfaces.user.dto.AgentDto;
import com.blackdog.dabang.interfaces.user.dto.AgentDto.AgentAddRequest;
import com.blackdog.dabang.interfaces.user.dto.UserDto;
import com.blackdog.dabang.interfaces.user.dto.UserDto.UserAgentJoinRequest;
import com.blackdog.dabang.interfaces.user.dto.UserDto.UserJoinRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    private static final String BASE_URL = "/api/v1/users";

    @Test
    void 일반유저등록() throws Exception {
        UserJoinRequest request = new UserJoinRequest("흑구", "test", "test");

        mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.seq").exists())
               .andExpect(jsonPath("$.result.name").exists())
               .andExpect(jsonPath("$.result.id").exists());
    }

    @Test
    void 부동산유저등록() throws Exception {
        UserJoinRequest userJoinRequest = new UserJoinRequest("흑구", "test", "test");
        AgentAddRequest agentAddRequest = new AgentAddRequest("흑구 부동산", "agent-10203910", "02-0000-1111");
        UserAgentJoinRequest request = new UserAgentJoinRequest(userJoinRequest, agentAddRequest);

        mockMvc.perform(post(BASE_URL + "/agent")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.user").exists())
                .andExpect(jsonPath("$.result.user.seq").exists())
                .andExpect(jsonPath("$.result.user.name").exists())
                .andExpect(jsonPath("$.result.user.id").exists())
                .andExpect(jsonPath("$.result.user.type").exists())
                .andExpect(jsonPath("$.result.agent").exists())
                .andExpect(jsonPath("$.result.agent.id").exists())
                .andExpect(jsonPath("$.result.agent.name").exists())
                .andExpect(jsonPath("$.result.agent.tel").exists());
    }

}