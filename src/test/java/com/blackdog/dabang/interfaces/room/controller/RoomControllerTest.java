package com.blackdog.dabang.interfaces.room.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.blackdog.dabang.TestConfig;
import com.blackdog.dabang.domain.room.Room;
import com.blackdog.dabang.domain.room.Room.RoomCount;
import com.blackdog.dabang.domain.room.Room.RoomPriceType;
import com.blackdog.dabang.domain.room.Room.RoomType;
import com.blackdog.dabang.domain.room.RoomReader;
import com.blackdog.dabang.interfaces.room.dto.RoomDto.AddRoomRequest;
import com.blackdog.dabang.interfaces.room.dto.RoomDto.EditRoomRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest(classes = TestConfig.class)
class RoomControllerTest {

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    private static final String BASE_URL = "/api/v1/rooms";

    private String roomId = "";

    @Autowired
    RoomReader roomReader;

    @BeforeEach
    void setUp() {
        // 매물 아이디 확인.
        List<Room> roomList = roomReader.getRoomList();
        roomId = roomList.stream().filter(Room::isClose).findFirst().get().getRoomId();
    }
    // 부동산유저
    private String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJEQUJBTkctU0VSVkVSIEFQSSIsImlzcyI6IkRBQkFORy1VU0VSIiwiaWF0IjoxNjYzODEwOTU2LCJleHAiOjE2NjQwOTg5NTYsInNlcSI6MiwidXNlcklkIjoiYWdlbnRfdGVzdCIsInR5cGUiOiJBR0VOVCJ9.KL4VwrE21U5_PZEzt93smcg_bLZpDeiRF4-r1dvUQ6E";
//        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJEQUJBTkctU0VSVkVSIEFQSSIsImlzcyI6IkRBQkFORy1VU0VSIiwiaWF0IjoxNjYzODExMDEwLCJleHAiOjE2NjQwOTkwMTAsInNlcSI6MSwidXNlcklkIjoidGVzdCIsInR5cGUiOiJOT1JNQUwifQ.eEEBV3CKRHxICPewv2JsUHr1iPYpzW9ZiGoYA2rs02w";

    @Test
    void editRoom() throws Exception {
        System.out.println("=============== editRoom Test Start ===============");
        EditRoomRequest request = new EditRoomRequest(
                RoomType.BILLA,
                RoomCount.THREEROOM,
                "서울특별시 관악구 신림동",
                RoomPriceType.MONTH,
                10_000_000L,
                500_000L,
                150_000L
        );

        mockMvc.perform(put(BASE_URL + "/{roomId}", roomId)
                       .contentType(MediaType.APPLICATION_JSON)
                       .accept(MediaType.APPLICATION_JSON)
                       .content(objectMapper.writeValueAsString(request))
                       .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.result.seq").exists())
               .andExpect(jsonPath("$.result.roomId").exists())
               .andExpect(jsonPath("$.result.status").exists())
               .andExpect(jsonPath("$.result.type").exists())
               .andExpect(jsonPath("$.result.roomCount").exists())
               .andExpect(jsonPath("$.result.viewCount").exists())
               .andExpect(jsonPath("$.result.address").exists())
               .andExpect(jsonPath("$.result.priceType").exists())
               .andExpect(jsonPath("$.result.deposit").exists())
               .andExpect(jsonPath("$.result.monthPrice").exists())
               .andExpect(jsonPath("$.result.managePrice").exists());
    }

    @Test
    void addRoom() throws Exception {
        AddRoomRequest addRoomRequest = new AddRoomRequest(
                RoomType.BILLA,
                RoomCount.THREEROOM,
                "서울특별시 관악구 신림동",
                RoomPriceType.MONTH,
                10_000_000L,
                500_000L,
                150_000L
        );

        mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(addRoomRequest))
                       .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.seq").exists())
                .andExpect(jsonPath("$.result.roomId").exists())
                .andExpect(jsonPath("$.result.status").exists())
                .andExpect(jsonPath("$.result.type").exists())
                .andExpect(jsonPath("$.result.roomCount").exists())
                .andExpect(jsonPath("$.result.viewCount").exists())
                .andExpect(jsonPath("$.result.address").exists())
                .andExpect(jsonPath("$.result.priceType").exists())
                .andExpect(jsonPath("$.result.deposit").exists())
                .andExpect(jsonPath("$.result.monthPrice").exists())
                .andExpect(jsonPath("$.result.managePrice").exists())
        ;
    }

    @Test
    void getRoomList() throws Exception {
        // TODO 검색필터 파라미터
        mockMvc.perform(get(BASE_URL))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.result").isArray())
               // 매물
               .andExpect(jsonPath("$.result[*].seq").exists())
               .andExpect(jsonPath("$.result[*].roomId").exists())
               .andExpect(jsonPath("$.result[*].status").exists())
               .andExpect(jsonPath("$.result[*].type").exists())
               .andExpect(jsonPath("$.result[*].roomCount").exists())
               .andExpect(jsonPath("$.result[*].viewCount").exists())
               .andExpect(jsonPath("$.result[*].address").exists())
               .andExpect(jsonPath("$.result[*].priceType").exists())
               .andExpect(jsonPath("$.result[*].deposit").exists())
               .andExpect(jsonPath("$.result[*].monthPrice").exists())
               .andExpect(jsonPath("$.result[*].managePrice").exists())
        ;
    }

    @Test
    void getRoomDetail() throws Exception {
        mockMvc.perform(get(BASE_URL + "/{roomId}", roomId))
               .andDo(print())
               .andExpect(status().isOk())
               // 매물
               .andExpect(jsonPath("$.result.room.seq").exists())
               .andExpect(jsonPath("$.result.room.roomId").exists())
               .andExpect(jsonPath("$.result.room.status").exists())
               .andExpect(jsonPath("$.result.room.type").exists())
               .andExpect(jsonPath("$.result.room.roomCount").exists())
               .andExpect(jsonPath("$.result.room.viewCount").exists())
               .andExpect(jsonPath("$.result.room.address").exists())
               .andExpect(jsonPath("$.result.room.priceType").exists())
               .andExpect(jsonPath("$.result.room.deposit").exists())
               .andExpect(jsonPath("$.result.room.monthPrice").exists())
               .andExpect(jsonPath("$.result.room.managePrice").exists())
               // 유저
               .andExpect(jsonPath("$.result.user.seq").exists())
               .andExpect(jsonPath("$.result.user.name").exists())
               .andExpect(jsonPath("$.result.user.id").exists())
               .andExpect(jsonPath("$.result.user.type").exists())
               // 부동산
               .andExpect(jsonPath("$.result.agent.id").exists())
               .andExpect(jsonPath("$.result.agent.name").exists())
               .andExpect(jsonPath("$.result.agent.tel").exists());
    }

}