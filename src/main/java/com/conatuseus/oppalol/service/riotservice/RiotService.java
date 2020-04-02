package com.conatuseus.oppalol.service.riotservice;

import com.conatuseus.oppalol.web.dto.RiotSummonerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class RiotService {

    private final RestTemplate restTemplate;

    @Value("${riot.api.summoner}")
    private String findSummonerApiUrl;

    public RiotService(final RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public RiotSummonerResponse findSummoner(final String summonerName) {
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(findSummonerApiUrl)
            .buildAndExpand(summonerName);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.ACCEPT_LANGUAGE, "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7");
        httpHeaders.set(HttpHeaders.ACCEPT_CHARSET, "application/x-www-form-urlencoded; charset=UTF-8");
        httpHeaders.set(HttpHeaders.ORIGIN, "https://developer.riotgames.com");
        // TODO: RIOT API KEY를 private 레포에서 가져오도록 하기
//        httpHeaders.set("X-Riot-Token","");

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<RiotSummonerResponse> responseEntity = restTemplate.exchange(uriComponents.toUriString(), HttpMethod.GET, httpEntity, RiotSummonerResponse.class);

        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new IllegalArgumentException("해당 사용자를 찾을 수 없습니다.");
        }
        return responseEntity.getBody();
    }
}
