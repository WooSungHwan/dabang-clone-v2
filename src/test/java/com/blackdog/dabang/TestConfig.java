package com.blackdog.dabang;

import com.blackdog.dabang.application.room.RoomFacade;
import com.blackdog.dabang.application.user.UserFacade;
import com.blackdog.dabang.domain.room.Room.RoomCount;
import com.blackdog.dabang.domain.room.Room.RoomPriceType;
import com.blackdog.dabang.domain.room.Room.RoomType;
import com.blackdog.dabang.domain.room.RoomCommand.AddRoomCommand;
import com.blackdog.dabang.domain.user.User.UserType;
import com.blackdog.dabang.domain.user.UserCommand.UserJoinCommand;
import com.blackdog.dabang.domain.user.agent.AgentCommand.AgentAddCommand;
import com.blackdog.dabang.interfaces.user.dto.UserDto.UserAgentJoinResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private RoomFacade roomFacade;

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
            addNormalUser();
            UserAgentJoinResponse userAgentJoinResponse = addAgentUser();
            addRoom(userAgentJoinResponse);
        };
    }

    private void addRoom(UserAgentJoinResponse userAgentJoinResponse) {
        AddRoomCommand command = AddRoomCommand.builder()
                                                     .type(RoomType.APT)
                                                     .roomCount(RoomCount.ONEROOM)
                                                     .address("서울특별시 관악구 신림동")
                                                     .priceType(RoomPriceType.MONTH)
                                                     .deposit(10_000_000L)
                                                     .monthPrice(500_000L)
                                                     .managePrice(150_000L)
                                                     .userSeq(userAgentJoinResponse.getUser()
                                                                                   .getSeq())
                                                     .build();
        roomFacade.addRoom(command);
    }

    private UserAgentJoinResponse addAgentUser() {
        UserJoinCommand agentUserCommand = UserJoinCommand.builder()
                                                           .id("agent_test")
                                                           .password("agent_test_password")
                                                           .name("agent_test유저")
                                                           .userType(UserType.AGENT)
                                                           .build();

        AgentAddCommand agentAddCommand = AgentAddCommand.builder()
                                               .agentName("test부동산")
                                               .businessId("test_businessId")
                                               .tel("02-0000-1111")
                                               .build();

        return userFacade.agentJoin(agentUserCommand, agentAddCommand);
    }

    private void addNormalUser() {
        UserJoinCommand normalUserCommand = UserJoinCommand.builder()
                                               .id("test")
                                               .password("test_password")
                                               .name("test유저")
                                               .userType(UserType.NORMAL)
                                               .build();

        userFacade.join(normalUserCommand);
    }

}
