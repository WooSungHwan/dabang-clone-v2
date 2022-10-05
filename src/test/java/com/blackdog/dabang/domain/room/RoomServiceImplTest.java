package com.blackdog.dabang.domain.room;

import static com.blackdog.dabang.TestConfig.JWT_TOKEN_AGENT;
import static org.assertj.core.api.Assertions.assertThat;

import com.blackdog.dabang.TestConfig;
import com.blackdog.dabang.config.jwt.JwtProvider;
import com.blackdog.dabang.domain.room.Room.RoomCount;
import com.blackdog.dabang.domain.room.Room.RoomPriceType;
import com.blackdog.dabang.domain.room.Room.RoomStatus;
import com.blackdog.dabang.domain.room.Room.RoomType;
import com.blackdog.dabang.domain.room.RoomCommand.AddRoomCommand;
import com.blackdog.dabang.interfaces.room.dto.RoomDto.RoomResponse;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest(classes = TestConfig.class)
public class RoomServiceImplTest {

    @Autowired
    private RoomService service;

    @Autowired
    private JwtProvider jwtProvider;

    @Test
    void addRoom() {
        AddRoomCommand command = AddRoomCommand.builder()
                                               .userSeq(2L)
                                               .type(RoomType.APT)
                                               .roomCount(RoomCount.ONEROOM)
                                               .address("서울특별시 관악구 신림동")
                                               .priceType(RoomPriceType.MONTH)
                                               .deposit(100_000_000L)
                                               .monthPrice(500_000L)
                                               .managePrice(150_000L)
                                               .build();

        RoomResponse response = service.addRoom(command);
        assertThat(response).isNotNull();
        assertThat(response).extracting("seq").isNotNull();
        assertThat(response).extracting("roomId").isNotNull();
        assertThat(response).extracting("status").isEqualTo(RoomStatus.PROCEED);
        assertThat(response).extracting("type").isEqualTo(command.getType());
        assertThat(response).extracting("priceType").isEqualTo(command.getPriceType());
        assertThat(response).extracting("deposit").isEqualTo(command.getDeposit());
        assertThat(response).extracting("monthPrice").isEqualTo(command.getMonthPrice());
        assertThat(response).extracting("managePrice").isEqualTo(command.getManagePrice());
        assertThat(response).extracting("roomCount").isEqualTo(command.getRoomCount());
        assertThat(response).extracting("address").isEqualTo(command.getAddress());
        assertThat(response).extracting("viewCount").isEqualTo(0);
        assertThat(response).extracting("userSeq").isEqualTo(command.getUserSeq());
    }

    @Test
    void getMyRoomList() {
        Long userSeq = jwtProvider.getSeq(JWT_TOKEN_AGENT);
        List<RoomResponse> myRoomList = service.getMyRoomList(userSeq);
        long count = myRoomList.stream()
                               .filter(room -> room.getUserSeq() != userSeq)
                               .count();

        assertThat(count).isEqualTo(0);
    }

}
