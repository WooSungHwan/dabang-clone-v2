package com.blackdog.dabang.domain.room;

import java.util.List;

public interface RoomReader {

    Room getRoomBySeq(Long seq);

    List<Room> getRoomList();

    Room getRoomByRoomId(String roomId);

    int getRoomCount(Long userSeq);

}
