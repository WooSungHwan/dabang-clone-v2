package com.blackdog.dabang.domain.room;

import com.blackdog.dabang.common.exception.DabangBusinessException;
import com.blackdog.dabang.domain.room.RoomCommand.AddRoomCommand;
import com.blackdog.dabang.domain.room.RoomCommand.EditRoomCommand;
import com.blackdog.dabang.interfaces.room.dto.RoomDto.RoomResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    public static final int MAX_ROOM_COUNT = 20;

    private final RoomReader reader;

    private final RoomStore store;

    @Override
    public List<RoomResponse> getRoomList() {
        List<Room> roomList = reader.getRoomList();
        return roomList.stream()
                       .map(RoomResponse::new)
                       .toList();
    }

    @Override
    public RoomResponse getRoomDetail(Long seq) {
        Room room = reader.getRoomBySeq(seq);
        return new RoomResponse(room);
    }

    @Override
    public RoomResponse getRoomDetailByRoomId(String roomId) {
        Room room = reader.getRoomByRoomId(roomId);
        return new RoomResponse(room);
    }

    @Transactional
    @Override
    public RoomResponse addRoom(AddRoomCommand command) {
        int roomCount = reader.getRoomCount(command.getUserSeq());
        if (roomCount > MAX_ROOM_COUNT) {
            throw new DabangBusinessException(String.format("매물은 최대 %s개까지만 등록할 수 있습니다."));
        }
        Room room = store.addRoom(command.toEntity());
        return new RoomResponse(room);
    }

    @Transactional
    @Override
    public RoomResponse editRoom(String roomId,
                                 EditRoomCommand command) {
        Room room = reader.getRoomByRoomId(roomId);
        validateIsMyRoom(command.getUserSeq(), room); // 내 매물인지 확인.
        validateIsClose(room); // 광고종료 매물인지 확인.

        room.edit(command);
        return new RoomResponse(room);
    }

    @Transactional
    @Override
    public void closeRoom(String roomId) {
        Room room = reader.getRoomByRoomId(roomId);
        room.roomClose();
    }

    private void validateIsClose(Room room) {
        if (!room.isClose()) {
            throw new DabangBusinessException("광고종료 상태인 매물만 수정할 수 있습니다.");
        }
    }

    private void validateIsMyRoom(Long userSeq,
                                  Room room) {
        if (!room.isMine(userSeq)) {
            throw new DabangBusinessException("매물 수정 권한이 없습니다.");
        }
    }

}
