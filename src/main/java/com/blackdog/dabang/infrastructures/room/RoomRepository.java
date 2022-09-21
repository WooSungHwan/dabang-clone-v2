package com.blackdog.dabang.infrastructures.room;

import com.blackdog.dabang.domain.room.Room;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Optional<Room> findByRoomId(String roomId);

    int countByUserSeq(Long userSeq);

}