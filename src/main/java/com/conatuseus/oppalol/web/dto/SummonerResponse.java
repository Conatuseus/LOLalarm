package com.conatuseus.oppalol.web.dto;

import com.conatuseus.oppalol.domain.summoner.Summoner;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SummonerResponse {

    private String encryptedId;
    private String name;
    private String accountId;
    private String puuId;
    private Long summonerLevel;

    @Builder
    public SummonerResponse(final String encryptedId, final String name, final String accountId, final String puuId, final Long summonerLevel) {
        this.encryptedId = encryptedId;
        this.name = name;
        this.accountId = accountId;
        this.puuId = puuId;
        this.summonerLevel = summonerLevel;
    }

    public SummonerResponse(final Summoner summoner) {
        this.encryptedId = summoner.getEncryptedId();
        this.name = summoner.getName();
        this.accountId = summoner.getAccountId();
        this.puuId = summoner.getPuuId();
        this.summonerLevel = summoner.getSummonerLevel();
    }

    public SummonerResponse(final RiotSummonerResponse riotSummonerResponse) {
        this.encryptedId = riotSummonerResponse.getId();
        this.name = riotSummonerResponse.getName();
        this.accountId = riotSummonerResponse.getAccountId();
        this.puuId = riotSummonerResponse.getPuuid();
        this.summonerLevel = riotSummonerResponse.getSummonerLevel();
    }
}
