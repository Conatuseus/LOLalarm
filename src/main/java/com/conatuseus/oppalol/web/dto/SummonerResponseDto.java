package com.conatuseus.oppalol.web.dto;

import com.conatuseus.oppalol.domain.summoner.Summoner;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SummonerResponseDto {

    private Long id;
    private String encryptedId;
    private String name;
    private String accountId;
    private String puuId;
    private Long summonerLevel;

    @Builder
    public SummonerResponseDto(final Long id, final String encryptedId, final String name, final String accountId, final String puuId, final Long summonerLevel) {
        this.id = id;
        this.encryptedId = encryptedId;
        this.name = name;
        this.accountId = accountId;
        this.puuId = puuId;
        this.summonerLevel = summonerLevel;
    }

    public SummonerResponseDto(final Summoner summoner) {
        this.id = summoner.getId();
        this.encryptedId = summoner.getEncryptedId();
        this.name = summoner.getName();
        this.accountId = summoner.getAccountId();
        this.puuId = summoner.getPuuId();
        this.summonerLevel = summoner.getSummonerLevel();
    }

    public SummonerResponseDto(final RiotSummonerResponseDto riotSummonerResponseDto) {
        this.encryptedId = riotSummonerResponseDto.getId();
        this.name = riotSummonerResponseDto.getName();
        this.accountId = riotSummonerResponseDto.getAccountId();
        this.puuId = riotSummonerResponseDto.getPuuid();
        this.summonerLevel = riotSummonerResponseDto.getSummonerLevel();
    }
}
