package com.conatuseus.oppalol.service;

import com.conatuseus.oppalol.domain.summoner.SummonerRepository;
import com.conatuseus.oppalol.web.dto.RiotSummonerResponseDto;
import com.conatuseus.oppalol.web.dto.SummonerResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SummonerService {

    private final RiotService riotService;
    private final SummonerRepository summonerRepository;

    public SummonerResponseDto findSummoner(final String summonerName) {
        RiotSummonerResponseDto riotSummonerResponseDto = riotService.findSummoner(summonerName);
        return new SummonerResponseDto(riotSummonerResponseDto);
    }
}
