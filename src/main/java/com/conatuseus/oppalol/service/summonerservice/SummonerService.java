package com.conatuseus.oppalol.service.summonerservice;

import com.conatuseus.oppalol.service.riotservice.RiotService;
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
    private final SummonerInternalService summonerInternalService;

    public SummonerResponseDto findSummoner(final String summonerName) {
        if (summonerInternalService.existsByName(summonerName)) {
            return new SummonerResponseDto(summonerInternalService.findByName(summonerName));
        }
        RiotSummonerResponseDto riotSummonerResponseDto = riotService.findSummoner(summonerName);
        return new SummonerResponseDto(riotSummonerResponseDto);
    }

}
