package com.conatuseus.oppalol.domain.summonerregistration;

import com.conatuseus.oppalol.domain.base.BaseEntity;
import com.conatuseus.oppalol.domain.member.Member;
import com.conatuseus.oppalol.domain.summoner.Summoner;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@NoArgsConstructor
@Entity
public class SummonerRegistration extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @JsonBackReference
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "summoner_id")
    private Summoner summoner;

    @Builder
    public SummonerRegistration(final Member member, final Summoner summoner) {
        this.member = member;
        this.summoner = summoner;
    }
}
