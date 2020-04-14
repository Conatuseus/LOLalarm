package com.conatuseus.oppalol.domain.summoner;

import org.junit.After;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class SummonerRepositoryTest {

    @Autowired
    private SummonerRepository summonerRepository;

    @After
    public void cleanUp() {
        summonerRepository.deleteAll();
    }

    @Test
    public void 소환사_저장() {
        //given
        String encryptedId = "encryptedId";
        String name = "conatuseus";
        String accountId = "accountId";
        String puuId = "puuId";
        long summonerLevel = 100L;

        //when
        summonerRepository.save(Summoner.builder().encryptedId(encryptedId)
            .accountId(accountId)
            .name(name)
            .puuId(puuId)
            .summonerLevel(summonerLevel)
            .build());

        List<Summoner> summoners = summonerRepository.findAll();

        //then
        Summoner summoner = summoners.stream().filter(it -> it.getName().equals(name)).findFirst().get();
        assertThat(summoner.getName()).isEqualTo(name);
        assertThat(summoner.getEncryptedId()).isEqualTo(encryptedId);
    }

    @Test
    void 소환사_이름으로_찾기() {
        //given
        String encryptedId = "encryptedId";
        String name = "conatuseus";
        String accountId = "accountId";
        String puuId = "puuId";
        long summonerLevel = 100L;

        summonerRepository.save(Summoner.builder().encryptedId(encryptedId)
            .accountId(accountId)
            .name(name)
            .puuId(puuId)
            .summonerLevel(summonerLevel)
            .build());

        //when
        Summoner summoner = summonerRepository.findByName("conatuseus");

        //then
        assertThat(summoner.getName()).isEqualTo("conatuseus");
        assertThat(summoner.getEncryptedId()).isEqualTo("encryptedId");
    }

    @Test
    void 소환사_삭제() {
        //given
        String encryptedId = "encryptedId";
        String name = "conatuseus";
        String accountId = "accountId";
        String puuId = "puuId";
        long summonerLevel = 100L;

        Summoner summoner = Summoner.builder().encryptedId(encryptedId)
            .accountId(accountId)
            .name(name)
            .puuId(puuId)
            .summonerLevel(summonerLevel)
            .build();
        summonerRepository.save(summoner);

        //when
        summonerRepository.delete(summoner);

        //then
        assertThat(summonerRepository.existsByName(name)).isFalse();
    }
}