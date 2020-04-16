package com.conatuseus.oppalol.web;

import com.conatuseus.oppalol.service.summonerservice.SummonerService;
import com.conatuseus.oppalol.web.dto.SummonerResponseDto;
import com.conatuseus.oppalol.web.dto.SummonerSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping(SummonerController.API_V_1_SUMMONERS)
@RequiredArgsConstructor
public class SummonerController {

    public static final String API_V_1_SUMMONERS = "/api/v1/summoners";
    private final SummonerService summonerService;

    @GetMapping("/{summonerName}")
    public ResponseEntity<SummonerResponseDto> findSummonerByName(@PathVariable final String summonerName) {
        SummonerResponseDto summoner = summonerService.findSummoner(summonerName);
        return ResponseEntity.ok(summoner);
    }

    @PostMapping
    public ResponseEntity saveSummoner(@RequestBody final SummonerSaveRequestDto requestDto) {
        URI location = URI.create(API_V_1_SUMMONERS + "/" + summonerService.saveSummoner(requestDto).getName());
        return ResponseEntity.created(location).build();
    }
}
