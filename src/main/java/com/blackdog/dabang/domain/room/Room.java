package com.blackdog.dabang.domain.room;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;

import com.blackdog.dabang.common.exception.InvalidParameterException;
import com.blackdog.dabang.common.exception.InvalidStatusException;
import com.blackdog.dabang.domain.room.RoomCommand.EditRoomCommand;
import com.blackdog.dabang.domain.user.User;
import com.blackdog.dabang.domain.user.agent.Agent;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicUpdate;

@DynamicUpdate
@Slf4j
@Getter
@Entity
@NoArgsConstructor
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "room_id", length = 100)
    private String roomId;

    @Column(name = "user_seq")
    private Long userSeq;

    @Enumerated(STRING)
    @Column(name = "status", length = 20)
    private RoomStatus status;

    @Enumerated(STRING)
    @Column(name = "type", length = 20)
    private RoomType type;

    @Enumerated(STRING)
    @Column(name = "price_type", length = 20)
    private RoomPriceType priceType;

    @Column(name = "deposit")
    private Long deposit; // 보증금

    @Column(name = "month_price")
    private Long monthPrice; // 월세

    @Column(name = "manage_price")
    private Long managePrice; //관리비

    @Enumerated(STRING)
    @Column(name = "room_count", length = 20)
    private RoomCount roomCount;

    @Column(name = "view_count")
    private Integer viewCount;

    @Column(name = "address")
    private String address;

    @Builder
    public Room(Long userSeq, RoomType type, RoomCount roomCount, String address, RoomPriceType priceType, Long deposit, Long monthPrice, Long managePrice) {
        if (Objects.isNull(userSeq)) throw new InvalidParameterException("Room.userSeq");
        if (Objects.isNull(type)) throw new InvalidParameterException("Room.type");
        if (Objects.isNull(roomCount)) throw new InvalidParameterException("Room.roomCount");
        if (StringUtils.isBlank(address)) throw new InvalidParameterException("Room.address");
        if (Objects.isNull(priceType)) throw new InvalidParameterException("Room.priceType");
        if (Objects.isNull(deposit)) throw new InvalidParameterException("Room.deposit");
        if (Objects.isNull(monthPrice)) throw new InvalidParameterException("Room.monthPrice");
        if (Objects.isNull(managePrice)) throw new InvalidParameterException("Room.managePrice");


        this.type = type;
        this.roomCount = roomCount;
        this.address = address;
        this.priceType = priceType;
        this.deposit = deposit;
        if (priceType == RoomPriceType.MONTH) {
           this.monthPrice = monthPrice;
        }
        this.managePrice = managePrice;
        this.userSeq = userSeq;

        this.roomId = UUID.randomUUID().toString();
        this.status = RoomStatus.PROCEED;
        this.viewCount = 0;
    }

    public void roomClose() {
        if (this.status == RoomStatus.CLOSE) {
            log.error("매물 상태를 확인해주세요. 매물번호 : {}", seq);
            throw new InvalidStatusException("매물 상태를 확인해주세요.");
        }
        this.status = RoomStatus.CLOSE;
    }

    public void roomProceed() {
        if (this.status == RoomStatus.PROCEED) {
            log.error("매물 상태를 확인해주세요. 매물번호 : {}", seq);
            throw new InvalidStatusException("매물 상태를 확인해주세요.");
        }
        this.status = RoomStatus.PROCEED;
    }

    public boolean isMine(Long userSeq) {
        return Objects.equals(this.userSeq, userSeq);
    }

    public boolean isClose() {
        return this.status == RoomStatus.CLOSE;
    }

    public void edit(EditRoomCommand command) {
        this.type = command.getType();
        this.roomCount = command.getRoomCount();
        this.address = command.getAddress();
        this.priceType = command.getPriceType();
        this.deposit = command.getDeposit();
        this.monthPrice = command.getMonthPrice();
        this.managePrice = command.getManagePrice();
    }

    @Getter
    @AllArgsConstructor
    public enum RoomStatus {
        PROCEED("광고중"),
        CLOSE("광고종료");

        private String name;
    }

    @Getter
    @AllArgsConstructor
    public enum RoomCount {
        ONEROOM("원룸"),
        TWOROOM("투룸"),
        THREEROOM("쓰리룸"),
        MORE("4개 이상");

        private String name;
    }

    @Getter
    @AllArgsConstructor
    public enum RoomType {
        BILLA("일반주택"),
        OFFICETEL("오피스텔"),
        APT("아파트");

        private String name;
    }

    @Getter
    @AllArgsConstructor
    public enum RoomPriceType {
        MONTH("월세"),
        LEASE("전세"),
        TRADE("매매");

        private String name;
    }

}
