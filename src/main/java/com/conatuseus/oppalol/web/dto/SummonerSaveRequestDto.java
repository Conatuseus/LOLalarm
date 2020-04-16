package com.conatuseus.oppalol.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SummonerSaveRequestDto {

    private String summonerName;

    @Builder
    public SummonerSaveRequestDto(final String summonerName) {
        this.summonerName = summonerName;
    }
}