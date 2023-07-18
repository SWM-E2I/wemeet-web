package com.e2i.wemeet.web.exception.notfound;

public class CollegeNotFoundException extends NotFoundException {
    private static final String MESSAGE = "대학교를 찾을 수 없습니다.";

    public CollegeNotFoundException() {
        super(MESSAGE);
    }
}
