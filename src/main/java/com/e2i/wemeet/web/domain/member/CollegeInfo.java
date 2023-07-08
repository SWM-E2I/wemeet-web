package com.e2i.wemeet.web.domain.member;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class CollegeInfo {

    @Column(length = 30, nullable = false)
    private String college;

    @Column(length = 20, nullable = false)
    private String collegeType;

    @Column(length = 2, nullable = false)
    private String admissionYear;

    @Column(length = 60, unique = true)
    private String mail;

    @Builder
    public CollegeInfo(String college, String collegeType, String admissionYear, String mail) {
        this.college = college;
        this.collegeType = collegeType;
        this.admissionYear = admissionYear;
        this.mail = mail;
    }

    public void saveMail(String mail) {
        this.mail = mail;
    }
}
