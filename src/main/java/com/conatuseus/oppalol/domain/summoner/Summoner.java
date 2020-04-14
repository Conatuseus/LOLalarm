package com.conatuseus.oppalol.domain.summoner;

import com.conatuseus.oppalol.domain.base.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@NoArgsConstructor
@Entity
public class Summoner extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String encryptedId;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String accountId;

    @Column(unique = true, nullable = false)
    private String puuId;

    @Column(nullable = false)
    private Long summonerLevel;

    @Column(nullable = false)
    private int profileIconId;

    @Builder
    public Summoner(final String encryptedId, final String name, final String accountId, final String puuId, final Long summonerLevel, final int profileIconId) {
        this.encryptedId = encryptedId;
        this.name = name;
        this.accountId = accountId;
        this.puuId = puuId;
        this.summonerLevel = summonerLevel;
        this.profileIconId = profileIconId;
    }
}
