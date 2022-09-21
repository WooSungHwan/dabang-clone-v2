package com.blackdog.dabang.interfaces.room.controller;

import com.blackdog.dabang.application.room.RoomFacade;
import com.blackdog.dabang.common.response.success.RestResponse;
import com.blackdog.dabang.config.security.SecurityUserDetails;
import com.blackdog.dabang.interfaces.room.dto.RoomDto.AddRoomRequest;
import com.blackdog.dabang.interfaces.room.dto.RoomDto.RoomDetailResponse;
import com.blackdog.dabang.interfaces.room.dto.RoomDto.RoomResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rooms")
public class RoomController {

    private final RoomFacade facade;

    /**
     * 매물 등록
     * @param user
     * @param request
     * @return
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestResponse> addRoom(@AuthenticationPrincipal SecurityUserDetails user,
                                                @RequestBody @Validated AddRoomRequest request) {
        RoomResponse roomResponse = facade.addRoom(request.toCommand(user.getSeq()));
        RestResponse response = RestResponse.of(roomResponse);
        return ResponseEntity.ok(response);
    }

    /**
     * 매물 목록 조회
     * @return
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestResponse> getRoomList() {
        List<RoomResponse> roomResponseList = facade.getRoomList();
        RestResponse response = RestResponse.of(roomResponseList);
        return ResponseEntity.ok(response);
    }

    /**
     * <pre>
     *     매물 아이디로 매물 상세 조회
     * </pre>
     * @param roomId
     * @return
     */
    @GetMapping(value = "/{roomId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestResponse> getRoomDetail(@PathVariable("roomId") String roomId) {
        RoomDetailResponse roomDetailResponse = facade.getRoomDetail(roomId);
        RestResponse response = RestResponse.of(roomDetailResponse);
        return ResponseEntity.ok(response);
    }

}
