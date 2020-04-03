package com.conatuseus.oppalol.service.riotservice;

import com.conatuseus.oppalol.web.dto.RiotSummonerResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
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

    public static final String RIOT_API_KEY = "riotAPIKey";
    private final RestTemplate restTemplate;
    private final RedisTemplate<String, Object> redisTemplate;

    @Value("${riot.api.summoner}")
    private String findSummonerApiUrl;

    public RiotService(final RestTemplateBuilder restTemplateBuilder, final RedisTemplate<String, Object> redisTemplate) {
        this.restTemplate = restTemplateBuilder.build();
        this.redisTemplate = redisTemplate;
    }

    public RiotSummonerResponse findSummoner(final String summonerName) {
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(findSummonerApiUrl)
            .buildAndExpand(summonerName);

        HttpHeaders httpHeaders = new HttpHeaders();
        ValueOperations<String, Object> vop = redisTemplate.opsForValue();
        String riotAPIKey = (String) vop.get(RIOT_API_KEY);
        httpHeaders.set("X-Riot-Token", riotAPIKey);

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<RiotSummonerResponse> responseEntity = restTemplate.exchange(uriComponents.toUriString(), HttpMethod.GET, httpEntity, RiotSummonerResponse.class);

        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new IllegalArgumentException("해당 사용자를 찾을 수 없습니다.");
        }
        return responseEntity.getBody();
    }
}
