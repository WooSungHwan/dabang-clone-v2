package com.blackdog.dabang.interfaces.user.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import com.blackdog.dabang.interfaces.user.dto.AgentDto.AgentAddRequest;
import com.blackdog.dabang.interfaces.user.dto.UserDto.UserAgentJoinRequest;
import com.blackdog.dabang.interfaces.user.dto.UserDto.UserJoinRequest;
import com.blackdog.dabang.interfaces.user.dto.UserDto.UserLoginRequest;
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
    void 일반유저등록_성공(String name, String id, String password) {
        UserJoinRequest userJoinRequest = new UserJoinRequest(
                name,
                id,
                password
        );

        Validators.validate(userJoinRequest);
    }

    @ArgumentsSource(FailUserJoinRequestProvider.class)
    @ParameterizedTest
    void 일반유저등록_실패(String name, String id, String password) {
        UserJoinRequest userJoinRequest = new UserJoinRequest(
                name,
                id,
                password
        );

        validateFromThrowable(userJoinRequest);
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

    @ArgumentsSource(SuccessUserAgentJoinRequestProvider.class)
    @ParameterizedTest
    void 부동산유저등록_성공(String name, String id, String password, String agentName, String businessId, String tel) {
        UserJoinRequest userJoinRequest = new UserJoinRequest(name, id, password);
        AgentAddRequest agentAddRequest = new AgentAddRequest(agentName, businessId, tel);

        Validators.validate(new UserAgentJoinRequest(userJoinRequest, agentAddRequest));
    }

    @ArgumentsSource(FailUserAgentJoinRequestProvider.class)
    @ParameterizedTest
    void 부동산유저등록_실패(String name, String id, String password, String agentName, String businessId, String tel) {
        UserJoinRequest userJoinRequest = new UserJoinRequest(name, id, password);
        AgentAddRequest agentAddRequest = new AgentAddRequest(agentName, businessId, tel);

        UserAgentJoinRequest userAgentJoinRequest = new UserAgentJoinRequest(userJoinRequest, agentAddRequest);

        validateFromThrowable(userAgentJoinRequest);
    }

    private static class SuccessUserAgentJoinRequestProvider extends SuccessUserJoinRequestProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of(
                    Arguments.of("흑구", "test", "test", "서초공인중개사무소", "110-20-30491", "02-1588-1588")
            );
        }
    }

    private static class FailUserAgentJoinRequestProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of(
                    // 이름, 아이디, 비밀번호
                    Arguments.of(null, null, null, "서초공인중개사무소", "110-20-30491", "02-1588-1588"),
                    Arguments.of("", "", null, "서초공인중개사무소", "110-20-30491", "02-1588-1588"),
                    Arguments.of("", "", "", "서초공인중개사무소", "110-20-30491", "02-1588-1588"),
                    Arguments.of("흑구", "", "", "서초공인중개사무소", "110-20-30491", "02-1588-1588"),
                    Arguments.of("흑구", null, null, "서초공인중개사무소", "110-20-30491", "02-1588-1588"),
                    Arguments.of("흑구", "test", "test", null, null, null),
                    Arguments.of("흑구", "test", "test", "", "", null),
                    Arguments.of("흑구", "test", "test", "", "", ""),
                    Arguments.of("흑구", "test", "test", "서초공인중개사무소", "", ""),
                    Arguments.of("흑구", "test", "test", "서초공인중개사무소", null, null)
            );
        }
    }

    @ArgumentsSource(SuccessUserLoginRequestProvider.class)
    @ParameterizedTest
    void 유저로그인_성공(String id, String password) {
        UserLoginRequest userLoginRequest = new UserLoginRequest(id, password);

        Validators.validate(userLoginRequest);
    }

    private static class SuccessUserLoginRequestProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of(
                    // 아이디, 비밀번호
                    Arguments.of("blackdog", "blackdogPassword")
            );
        }
    }

    @ArgumentsSource(FailUserLoginRequestProvider.class)
    @ParameterizedTest
    void 유저로그인_실패(String id, String password) {
        UserLoginRequest userLoginRequest = new UserLoginRequest(id, password);
        validateFromThrowable(userLoginRequest);
    }

    private static class FailUserLoginRequestProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of(
                    // 아이디, 비밀번호
                    Arguments.of("", "blackdogPassword"),
                    Arguments.of("blackdog", ""),
                    Arguments.of("blackdog", null),
                    Arguments.of(null, "blackdogPassword"),
                    Arguments.of(null, null)
            );
        }
    }

    private <T> void validateFromThrowable(T request) {
        Throwable throwable = catchThrowable(() -> Validators.validate(request));
        assertThat(throwable).isInstanceOf(ConstraintViolationException.class);
    }
}
