package com.blackdog.dabang.interfaces.room.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import com.blackdog.dabang.domain.room.Room.RoomCount;
import com.blackdog.dabang.domain.room.Room.RoomPriceType;
import com.blackdog.dabang.domain.room.Room.RoomType;
import com.blackdog.dabang.interfaces.room.dto.RoomDto.AddRoomRequest;
import com.blackdog.dabang.util.Validators;
import java.util.stream.Stream;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

@Slf4j
public class RoomDtoTest {

    @ArgumentsSource(SuccessAddRoomRequest.class)
    @ParameterizedTest
    void 매물등록_성공(RoomType type, RoomCount roomCount, String address, RoomPriceType priceType, Long deposit, Long monthPrice, Long managePrice) {
        AddRoomRequest addRoomRequest = new AddRoomRequest(type, roomCount, address, priceType, deposit, monthPrice, managePrice);
        Validators.validate(addRoomRequest);
    }

    @ArgumentsSource(FailAddRoomRequest.class)
    @ParameterizedTest
    void 매물등록_실패(RoomType type, RoomCount roomCount, String address, RoomPriceType priceType, Long deposit, Long monthPrice, Long managePrice) {
        AddRoomRequest addRoomRequest = new AddRoomRequest(type, roomCount, address, priceType, deposit, monthPrice, managePrice);
        validateFromThrowable(addRoomRequest);
    }

    private static class SuccessAddRoomRequest implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of(
                    Arguments.of(RoomType.BILLA, RoomCount.ONEROOM, "서울특별시 관악구 신림동", RoomPriceType.MONTH, 10_000_000L, 500_000L, 150_000L),
                    Arguments.of(RoomType.APT, RoomCount.TWOROOM, "서울특별시 관악구 신림동", RoomPriceType.LEASE, 10_000_000L, null, 150_000L),
                    Arguments.of(RoomType.APT, RoomCount.ONEROOM, "서울특별시 관악구 신림동", RoomPriceType.LEASE, 10_000_000L, null, 150_000L),
                    Arguments.of(RoomType.OFFICETEL, RoomCount.TWOROOM, "서울특별시 관악구 신림동", RoomPriceType.TRADE, 10_000_000L, null, 150_000L),
                    Arguments.of(RoomType.BILLA, RoomCount.THREEROOM, "서울특별시 관악구 신림동", RoomPriceType.MONTH, 10_000_000L, 500_000L, 150_000L),
                    Arguments.of(RoomType.APT, RoomCount.MORE, "서울특별시 관악구 신림동", RoomPriceType.LEASE, 10_000_000L, null, 150_000L),
                    Arguments.of(RoomType.OFFICETEL, RoomCount.THREEROOM, "서울특별시 관악구 신림동", RoomPriceType.TRADE, 10_000_000L, null, 150_000L),
                    Arguments.of(RoomType.BILLA, RoomCount.MORE, "서울특별시 관악구 신림동", RoomPriceType.MONTH, 10_000_000L, 500_000L, 150_000L)

            );
        }
    }

    private static class FailAddRoomRequest implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of(
                    Arguments.of(null, RoomCount.ONEROOM, "서울특별시 관악구 신림동", RoomPriceType.MONTH, 10_000_000L, 500_000L, 150_000L),
                    Arguments.of(RoomType.APT, null, "서울특별시 관악구 신림동", RoomPriceType.MONTH, 10_000_000L, 500_000L, 150_000L),
                    Arguments.of(RoomType.APT, RoomCount.MORE, null, RoomPriceType.MONTH, 10_000_000L, 500_000L, 150_000L),
                    Arguments.of(RoomType.OFFICETEL, RoomCount.THREEROOM, "", RoomPriceType.MONTH, 10_000_000L, 500_000L, 150_000L),
                    Arguments.of(RoomType.BILLA, RoomCount.TWOROOM, "서울특별시 관악구 신림동", null, 10_000_000L, 500_000L, 150_000L),
                    Arguments.of(RoomType.APT, RoomCount.ONEROOM, "서울특별시 관악구 신림동", RoomPriceType.MONTH, 10_000_000L, null, 150_000L),
                    Arguments.of(RoomType.OFFICETEL, RoomCount.MORE, "서울특별시 관악구 신림동", RoomPriceType.TRADE, 10_000_000L, 500_000L, 150_000L),
                    Arguments.of(RoomType.BILLA, RoomCount.THREEROOM, "서울특별시 관악구 신림동", RoomPriceType.LEASE, null, 500_000L, 150_000L),
                    Arguments.of(RoomType.BILLA, RoomCount.THREEROOM, "서울특별시 관악구 신림동", RoomPriceType.MONTH, 10_000_000L, 500_000L, null),
                    Arguments.of(RoomType.BILLA, RoomCount.THREEROOM, "서울특별시 관악구 신림동", RoomPriceType.LEASE, 10_000_000L, 500_000L, 150_000L),
                    Arguments.of(RoomType.BILLA, RoomCount.THREEROOM, "서울특별시 관악구 신림동", RoomPriceType.MONTH, null, null, null)
            );
        }

    }

    private <T> void validateFromThrowable(T request) {
        Throwable throwable = catchThrowable(() -> Validators.validate(request));
        assertThat(throwable).isInstanceOf(ConstraintViolationException.class);
    }

}
