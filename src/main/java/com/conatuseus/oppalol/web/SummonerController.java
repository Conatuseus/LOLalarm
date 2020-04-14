package com.conatuseus.oppalol.web;

import com.conatuseus.oppalol.service.summonerservice.SummonerService;
import com.conatuseus.oppalol.web.dto.SummonerResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(SummonerController.API_V_1_SUMMONERS)
@RequiredArgsConstructor
public class SummonerController {

    public static final String API_V_1_SUMMONERS = "/api/v1/summoners";
    private final SummonerService summonerService;

    @GetMapping("/{summonerName}")
    public ResponseEntity<SummonerResponseDto> findSummoner(@PathVariable final String summonerName) {
        SummonerResponseDto summoner = summonerService.findSummoner(summonerName);
        return ResponseEntity.ok(summoner);
    }
}
