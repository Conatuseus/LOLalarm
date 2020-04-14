package com.conatuseus.oppalol.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SummonerRegistrationRequestDto {

    private Long memberId;
    private String summonerName;

    @Builder
    public SummonerRegistrationRequestDto(final Long memberId, final String summonerName) {
        this.memberId = memberId;
        this.summonerName = summonerName;
    }
}