package com.blackdog.dabang.interfaces.user.controller;

import com.blackdog.dabang.common.response.success.RestResponse;
import com.blackdog.dabang.config.jwt.JwtProvider;
import com.blackdog.dabang.config.security.SecurityUserDetails;
import com.blackdog.dabang.interfaces.user.dto.UserDto.UserLoginRequest;
import com.blackdog.dabang.interfaces.user.dto.UserDto.UserLoginResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/login")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    /**
     * 유저 로그인
     * @param request
     * @return
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestResponse> login(@RequestBody @Validated UserLoginRequest request) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getId(), request.getPassword()));
        UserLoginResponse response = UserLoginResponse.of(jwtProvider.generateJwtToken((SecurityUserDetails) authenticate.getPrincipal()));
        return ResponseEntity.ok(RestResponse.of(response));
    }

}
