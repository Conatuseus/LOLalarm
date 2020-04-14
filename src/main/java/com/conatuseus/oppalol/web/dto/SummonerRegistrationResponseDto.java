package com.conatuseus.oppalol.web.dto;

import com.conatuseus.oppalol.domain.member.Member;
import com.conatuseus.oppalol.domain.summoner.Summoner;
import com.conatuseus.oppalol.domain.summonerregistration.SummonerRegistration;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SummonerRegistrationResponseDto {

    private Long id;
    private Member member;
    private Summoner summoner;

    @Builder
    public SummonerRegistrationResponseDto(final Long id, final Member member, final Summoner summoner) {
        this.id = id;
        this.member = member;
        this.summoner = summoner;
    }

    public SummonerRegistrationResponseDto(final SummonerRegistration summonerRegistration) {
        this.id = summonerRegistration.getId();
        this.member = summonerRegistration.getMember();
        this.summoner = summonerRegistration.getSummoner();
    }
}
