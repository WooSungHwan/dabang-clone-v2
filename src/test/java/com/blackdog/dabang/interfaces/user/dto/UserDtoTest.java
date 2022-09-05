package com.blackdog.dabang.interfaces.user.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import com.blackdog.dabang.interfaces.user.dto.UserDto.UserJoinRequest;
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
public class UserDtoTest {

    @ArgumentsSource(SuccessUserJoinRequestProvider.class)
    @ParameterizedTest
    void 유저등록_성공(String name, String id, String password) {
        UserJoinRequest userJoinRequest = new UserJoinRequest(
                name,
                id,
                password
        );

        Validators.validate(userJoinRequest);
    }

    @ArgumentsSource(FailUserJoinRequestProvider.class)
    @ParameterizedTest
    void 유저등록_실패(String name, String id, String password) {
        UserJoinRequest userJoinRequest = new UserJoinRequest(
                name,
                id,
                password
        );

        Throwable throwable = catchThrowable(() -> Validators.validate(userJoinRequest));
        assertThat(throwable).isInstanceOf(ConstraintViolationException.class);
    }


    private static class SuccessUserJoinRequestProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of(
                    // 이름, 아이디, 비밀번호
                    Arguments.of("흑구", "test", "test")
            );
       }
    }

    private static class FailUserJoinRequestProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of(
                    // 이름, 아이디, 비밀번호
                    Arguments.of(null, null, null),
                    Arguments.of("", "", null),
                    Arguments.of("", "", ""),
                    Arguments.of("흑구", "", ""),
                    Arguments.of("흑구", null, null)
            );
        }
    }

}
