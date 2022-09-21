package com.blackdog.dabang.application.room;

import com.blackdog.dabang.common.exception.DabangBaseException;
import com.blackdog.dabang.common.exception.DabangBusinessException;
import com.blackdog.dabang.domain.room.RoomCommand.AddRoomCommand;
import com.blackdog.dabang.domain.room.RoomService;
import com.blackdog.dabang.domain.user.User.UserType;
import com.blackdog.dabang.domain.user.UserService;
import com.blackdog.dabang.domain.user.agent.AgentService;
import com.blackdog.dabang.interfaces.room.dto.RoomDto.RoomDetailResponse;
import com.blackdog.dabang.interfaces.room.dto.RoomDto.RoomResponse;
import com.blackdog.dabang.interfaces.user.dto.AgentDto.AgentResponse;
import com.blackdog.dabang.interfaces.user.dto.UserDto.UserResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomFacade {

    private final RoomService service;
    private final UserService userService;
    private final AgentService agentService;

    public List<RoomResponse> getRoomList() {
        return service.getRoomList();
    }

    public RoomDetailResponse getRoomDetail(String roomId) {
        RoomResponse roomResponse = service.getRoomDetailByRoomId(roomId);
        UserResponse userResponse = userService.getUserBySeq(roomResponse.getUserSeq());
        AgentResponse agentResponse = agentService.getAgentByUserSeq(roomResponse.getUserSeq());

        return new RoomDetailResponse(roomResponse, userResponse, agentResponse);
    }

    public RoomResponse addRoom(AddRoomCommand command) {
        validateForUser(command);
        return service.addRoom(command);
    }

    private void validateForUser(AddRoomCommand command) {
        UserResponse user = userService.getUserBySeq(command.getUserSeq());
        if (user.getType() != UserType.AGENT) {
            throw new DabangBusinessException("부동산 유저만 매물을 등록할 수 있습니다.");
        }
    }

}
