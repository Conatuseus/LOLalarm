package com.conatuseus.oppalol.domain.summoner;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SummonerRepository extends JpaRepository<Summoner, Long> {

    boolean existsByName(String summonerName);

    Summoner findByName(String summonerName);
}
