package com.e2i.wemeet.web.domain.team;

import com.e2i.wemeet.web.domain.BaseTimeEntity;
import com.e2i.wemeet.web.domain.member.Gender;
import com.e2i.wemeet.web.domain.member.Member;
import com.e2i.wemeet.web.util.code.RandomCodeUtils;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TEAM")
@Entity
public class Team extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamId;

    @Column(nullable = false)
    private int memberCount;

    @Column(nullable = false)
    private String teamCode;

    @Column(length = 6, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    private boolean drinkingOption;

    @Column(length = 100)
    private String introduction;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member teamLeader;

    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

    @Builder
    public Team(int memberCount, boolean drinkingOption, String introduction) {
        this.memberCount = memberCount;
        this.drinkingOption = drinkingOption;
        this.introduction = introduction;
    }

    public void setTeamInformation(final Member teamLeader) {
        this.teamCode = RandomCodeUtils.createTeamCode(teamLeader);
        this.gender = teamLeader.getGender();
        this.teamLeader = teamLeader;
    }
}
