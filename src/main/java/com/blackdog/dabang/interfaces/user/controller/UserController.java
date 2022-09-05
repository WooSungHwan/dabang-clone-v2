package com.blackdog.dabang.interfaces.user.controller;

import com.blackdog.dabang.application.user.UserFacade;
import com.blackdog.dabang.common.response.success.RestResponse;
import com.blackdog.dabang.domain.user.UserCommand.UserJoinCommand;
import com.blackdog.dabang.interfaces.user.dto.UserDto.UserJoinRequest;
import com.blackdog.dabang.interfaces.user.dto.UserDto.UserJoinResponse;
import com.blackdog.dabang.interfaces.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserFacade facade;
    private final UserMapper mapper;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestResponse> join(@RequestBody @Validated UserJoinRequest userJoinRequest) {
        UserJoinCommand command = mapper.toUserJoinCommand(userJoinRequest);
        UserJoinResponse response = facade.join(command);
        return ResponseEntity.ok(RestResponse.of(response));
    }

}
