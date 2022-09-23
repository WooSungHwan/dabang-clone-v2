package com.blackdog.dabang.interfaces.room.dto;

import com.blackdog.dabang.domain.room.Room;
import com.blackdog.dabang.domain.room.Room.RoomCount;
import com.blackdog.dabang.domain.room.Room.RoomPriceType;
import com.blackdog.dabang.domain.room.Room.RoomStatus;
import com.blackdog.dabang.domain.room.Room.RoomType;
import com.blackdog.dabang.domain.room.RoomCommand.AddRoomCommand;
import com.blackdog.dabang.domain.room.RoomCommand.EditRoomCommand;
import com.blackdog.dabang.interfaces.user.dto.AgentDto.AgentResponse;
import com.blackdog.dabang.interfaces.user.dto.UserDto.UserResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Objects;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class RoomDto {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class RoomDetailResponse {

        private RoomResponse room;

        private UserResponse user;

        private AgentResponse agent;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class RoomResponse {

        private Long seq;

        private String roomId;

        private RoomStatus status;

        private RoomType type;

        private RoomPriceType priceType;

        private Long deposit;

        private Long monthPrice;

        private Long managePrice;

        private RoomCount roomCount;

        private String address;

        private Integer viewCount;

        @JsonIgnore
        private Long userSeq;

        public RoomResponse(Room room) {
            this.seq = room.getSeq();
            this.userSeq = room.getUserSeq();
            this.roomId = room.getRoomId();
            this.status = room.getStatus();
            this.type = room.getType();
            this.roomCount = room.getRoomCount();
            this.viewCount = room.getViewCount();
            this.address = room.getAddress();
            this.priceType = room.getPriceType();
            this.deposit = room.getDeposit();
            this.monthPrice = room.getMonthPrice();
            this.managePrice = room.getManagePrice();
        }
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class AddRoomRequest {
        @NotNull(message = "매물 유형을 입력해주세요.")
        private RoomType type;

        @NotNull(message = "매물 방 유형을 입력해주세요.")
        private RoomCount roomCount;

        @NotBlank(message = "매물 주소를 입력해주세요.")
        private String address;

        @NotNull(message = "매물 가격 유형을 입력해주세요.")
        private RoomPriceType priceType;

        @NotNull(message = "보증금을 입력해주세요.")
        private Long deposit;

        private Long monthPrice;

        @NotNull(message = "관리비를 입력해주세요.")
        private Long managePrice;

        @JsonIgnore
        @AssertTrue(message = "월세를 입력해주세요.")
        private boolean isValidMonthPriceNotNull() {
            if (priceType == RoomPriceType.MONTH) {
                return Objects.nonNull(monthPrice);
            }
            return true;
        }

        @JsonIgnore
        @AssertTrue(message = "월세를 입력할 수 없습니다.")
        private boolean isValidMonthPriceIsNull() {
            if (priceType != RoomPriceType.MONTH) {
                return Objects.isNull(monthPrice);
            }
            return true;
        }

        public AddRoomCommand toCommand(Long userSeq) {
            return AddRoomCommand.builder()
                    .userSeq(userSeq)
                    .type(type)
                    .roomCount(roomCount)
                    .address(address)
                    .priceType(priceType)
                    .deposit(deposit)
                    .monthPrice(monthPrice)
                    .managePrice(managePrice)
                    .build();
        }
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class EditRoomRequest {
        @NotNull(message = "매물 유형을 입력해주세요.")
        private RoomType type;

        @NotNull(message = "매물 방 유형을 입력해주세요.")
        private RoomCount roomCount;

        @NotBlank(message = "매물 주소를 입력해주세요.")
        private String address;

        @NotNull(message = "매물 가격 유형을 입력해주세요.")
        private RoomPriceType priceType;

        @NotNull(message = "보증금을 입력해주세요.")
        private Long deposit;

        private Long monthPrice;

        @NotNull(message = "관리비를 입력해주세요.")
        private Long managePrice;

        @JsonIgnore
        @AssertTrue(message = "월세를 입력해주세요.")
        private boolean isValidMonthPriceNotNull() {
            if (priceType == RoomPriceType.MONTH) {
                return Objects.nonNull(monthPrice);
            }
            return true;
        }

        @JsonIgnore
        @AssertTrue(message = "월세를 입력할 수 없습니다.")
        private boolean isValidMonthPriceIsNull() {
            if (priceType != RoomPriceType.MONTH) {
                return Objects.isNull(monthPrice);
            }
            return true;
        }

        public EditRoomCommand toCommand(Long userSeq) {
            return EditRoomCommand.builder()
                                 .userSeq(userSeq)
                                 .type(type)
                                 .roomCount(roomCount)
                                 .address(address)
                                 .priceType(priceType)
                                 .deposit(deposit)
                                 .monthPrice(monthPrice)
                                 .managePrice(managePrice)
                                 .build();
        }
    }

}
