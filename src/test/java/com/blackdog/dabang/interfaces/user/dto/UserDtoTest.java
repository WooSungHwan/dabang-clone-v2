package com.blackdog.dabang.interfaces.user.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import com.blackdog.dabang.interfaces.user.dto.AgentDto.AgentAddRequest;
import com.blackdog.dabang.interfaces.user.dto.UserDto.UserAgentJoinRequest;
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
        Throwable throwable = catchThrowable(() -> Validators.validate(userAgentJoinRequest));
        assertThat(throwable).isInstanceOf(ConstraintViolationException.class);
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

}
