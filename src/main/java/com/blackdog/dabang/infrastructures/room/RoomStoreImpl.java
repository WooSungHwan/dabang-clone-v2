package com.blackdog.dabang.infrastructures.room;

import com.blackdog.dabang.domain.room.Room;
import com.blackdog.dabang.domain.room.RoomStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RoomStoreImpl implements RoomStore {

    private final RoomRepository repository;

    @Override
    public Room addRoom(Room room) {
        return repository.save(room);
    }

}
