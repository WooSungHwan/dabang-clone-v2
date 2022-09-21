package com.blackdog.dabang.infrastructures.room;

import com.blackdog.dabang.common.exception.EntityNotFoundException;
import com.blackdog.dabang.domain.room.Room;
import com.blackdog.dabang.domain.room.RoomReader;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RoomReaderImpl implements RoomReader {

    private final RoomRepository repository;

    @Override
    public Room getRoomBySeq(Long seq) {
        return repository.findById(seq)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Room> getRoomList() {
        return repository.findAll(); // TODO 검색조건, pagination 추가 필요.
    }

    @Override
    public Room getRoomByRoomId(String roomId) {
        return repository.findByRoomId(roomId)
                         .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public int getRoomCount(Long userSeq) {
        return repository.countByUserSeq(userSeq);
    }

}
