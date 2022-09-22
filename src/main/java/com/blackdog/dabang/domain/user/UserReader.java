package com.blackdog.dabang.domain.user;

public interface UserReader {

    User getUserBySeq(Long seq);

    User getUserByUserId(String userId);

    boolean existsUserId(String userId);

}
