package com.blackdog.dabang.domain.room;

import com.blackdog.dabang.domain.room.Room.RoomCount;
import com.blackdog.dabang.domain.room.Room.RoomPriceType;
import com.blackdog.dabang.domain.room.Room.RoomType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class RoomCommand {

    @Getter
    @Builder
    @ToString
    public static class AddRoomCommand {
        private RoomType type;

        private RoomCount roomCount;

        private String address;

        private RoomPriceType priceType;

        private Long deposit;

        private Long monthPrice;

        private Long managePrice;

        private Long userSeq;

        public Room toEntity() {
            return Room.builder()
                    .userSeq(userSeq)
                    .roomCount(roomCount)
                    .type(type)
                    .address(address)
                    .priceType(priceType)
                    .deposit(deposit)
                    .monthPrice(monthPrice)
                    .managePrice(managePrice)
                    .build();
        }

    }

    @Getter
    @Builder
    @ToString
    public static class EditRoomCommand {
        private RoomType type;

        private RoomCount roomCount;

        private String address;

        private RoomPriceType priceType;

        private Long deposit;

        private Long monthPrice;

        private Long managePrice;

        private Long userSeq;
    }


}
