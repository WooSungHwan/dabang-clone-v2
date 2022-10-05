package com.blackdog.dabang.application.room;

import com.blackdog.dabang.common.exception.DabangBaseException;
import com.blackdog.dabang.common.exception.DabangBusinessException;
import com.blackdog.dabang.domain.room.RoomCommand.AddRoomCommand;
import com.blackdog.dabang.domain.room.RoomCommand.EditRoomCommand;
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
        validateForUser(command.getUserSeq(), "부동산 유저만 매물을 등록할 수 있습니다.");
        return service.addRoom(command);
    }

    public RoomResponse editRoom(String roomId,
                                 EditRoomCommand command) {
        validateForUser(command.getUserSeq(), "부동산 유저만 매물을 수정할 수 있습니다.");
        return service.editRoom(roomId, command);
    }

    public void closeRoom(Long userSeq,
                          String roomId) {
        validateForUser(userSeq, "부동산 유저만 매물을 수정할 수 있습니다.");
        service.closeRoom(roomId);
    }

    public void proceedRoom(Long userSeq, String roomId) {
        validateForUser(userSeq, "부동산 유저만 매물을 수정할 수 있습니다.");
        service.proceedRoom(roomId);
    }

    private void validateForUser(Long userSeq, String message) {
        UserResponse user = userService.getUserBySeq(userSeq);
        if (user.getType() != UserType.AGENT) {
            throw new DabangBusinessException(message);
        }
    }
}
