package com.conatuseus.oppalol.service.summonerservice;

import com.conatuseus.oppalol.domain.summoner.Summoner;
import com.conatuseus.oppalol.domain.summoner.SummonerRepository;
import com.conatuseus.oppalol.domain.summoner.exception.DuplicatedSummonerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class SummonerInternalService {

    private final SummonerRepository summonerRepository;

    public boolean existsByName(final String name) {
        return summonerRepository.existsByName(name);
    }

    public Summoner findByName(final String summonerName) {
        return summonerRepository.findByName(summonerName);
    }

    public Summoner saveSummoner(final Summoner summoner) {
        if (existsByName(summoner.getName())) {
            throw new DuplicatedSummonerException(summoner.getName());
        }
        return summonerRepository.save(summoner);
    }
}
