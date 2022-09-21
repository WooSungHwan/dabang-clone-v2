package com.blackdog.dabang.domain.room;

import com.blackdog.dabang.domain.room.RoomCommand.AddRoomCommand;
import com.blackdog.dabang.interfaces.room.dto.RoomDto.RoomResponse;
import java.util.List;

public interface RoomService {

    List<RoomResponse> getRoomList();

    RoomResponse getRoomDetail(Long seq);

    RoomResponse getRoomDetailByRoomId(String roomId);

    RoomResponse addRoom(AddRoomCommand command);

}
