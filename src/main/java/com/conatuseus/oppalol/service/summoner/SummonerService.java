package com.conatuseus.oppalol.service.summoner;

import com.conatuseus.oppalol.domain.summoner.SummonerRepository;
import com.conatuseus.oppalol.service.riotservice.RiotService;
import com.conatuseus.oppalol.web.dto.RiotSummonerResponse;
import com.conatuseus.oppalol.web.dto.SummonerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SummonerService {

    private final RiotService riotService;
    private final SummonerRepository summonerRepository;

    public SummonerResponse findSummoner(final String summonerName) {
        RiotSummonerResponse riotSummonerResponse = riotService.findSummoner(summonerName);
        return new SummonerResponse(riotSummonerResponse);
    }
}
