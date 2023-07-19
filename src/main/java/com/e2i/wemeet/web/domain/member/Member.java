package com.e2i.wemeet.web.domain.member;

import com.e2i.wemeet.web.domain.BaseTimeEntity;
import com.e2i.wemeet.web.domain.base.CryptoConverter;
import com.e2i.wemeet.web.domain.team.Team;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "MEMBER")
@Entity
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(length = 4, nullable = false)
    private String memberCode;

    @Column(length = 20, nullable = false)
    private String nickname;

    @Column(length = 6, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Convert(converter = CryptoConverter.class)
    @Column(length = 60, unique = true, nullable = false)
    private String phoneNumber;

    @Embedded
    private CollegeInfo collegeInfo;

    @Embedded
    private Preference preference;

    @Column(length = 7, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Mbti mbti;

    @Column(length = 100)
    private String introduction;

    @Column(nullable = false)
    private int credit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teamId")
    private Team team;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public Member(Long memberId, String memberCode, String nickname, Gender gender,
        String phoneNumber, CollegeInfo collegeInfo, Preference preference, Mbti mbti,
        String introduction, int credit, Team team, Role role) {
        this.memberId = memberId;
        this.memberCode = memberCode;
        this.nickname = nickname;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.collegeInfo = collegeInfo;
        this.preference = preference;
        this.mbti = mbti;
        this.introduction = introduction;
        this.credit = credit;
        this.team = team;
        this.role = role;
    }

    public void initTeamFromTeamLeader(final Team team) {
        setTeam(team);
        team.setTeamInformation(this);
    }

    public void setTeam(final Team team) {
        this.team = team;
        if (!team.getMembers().contains(this)) {
            team.getMembers().add(this);
        }
    }
}

