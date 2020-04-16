package com.conatuseus.oppalol.web;

import com.conatuseus.oppalol.global.security.SecurityConfig;
import com.conatuseus.oppalol.web.dto.SummonerRegistrationRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = {SecurityConfig.class}
)
@AutoConfigureWebTestClient
class SummonerRegistrationControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void saveRegistration() {
        //given
        SummonerRegistrationRequestDto requestDto = SummonerRegistrationRequestDto.builder()
            .memberId(999999999L)
            .summonerName("maldive")
            .build();

        //when
        WebTestClient.ResponseSpec exchange = webTestClient
            .post()
            .uri(SummonerRegistrationController.API_V_1_REGISTRATIONS)
            .body(Mono.just(requestDto), SummonerRegistrationRequestDto.class)
            .exchange();

        //then
        exchange.expectStatus()
            .isCreated()
            .expectBody()
            .jsonPath("$.member.id").isEqualTo(999999999L)
            .jsonPath("$.summoner.id").isEqualTo(999999998L);
    }
}