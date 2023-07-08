package com.e2i.wemeet.web.exception.notfound;

public class NotFoundTeamCodeException extends NotFoundException {

    private static final String MESSAGE = "팀 코드에 해당하는 팀이 존재하지 않습니다.";

    public NotFoundTeamCodeException() {
        super(MESSAGE);
    }
}