package com.conatuseus.oppalol.web;

import com.conatuseus.oppalol.global.security.SecurityConfig;
import com.conatuseus.oppalol.service.summonerservice.SummonerService;
import com.conatuseus.oppalol.web.dto.SummonerResponseDto;
import com.conatuseus.oppalol.web.dto.SummonerSaveRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = {SecurityConfig.class}
)
@AutoConfigureWebTestClient
class SummonerControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private SummonerService summonerService;

    @Test
    void findSummoner() {
        //given
        SummonerResponseDto summonerResponseDto = new SummonerResponseDto(987654324L, "encryptedId", "name", "accountId", "puuid", 100L);

        //when
        when(summonerService.findSummoner("name")).thenReturn(summonerResponseDto);

        //then
        webTestClient.get()
            .uri(SummonerController.API_V_1_SUMMONERS + "/name")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.encryptedId").isEqualTo("encryptedId")
            .jsonPath("$.name").isEqualTo("name")
            .jsonPath("$.summonerLevel").isEqualTo(100L);
    }

    @Test
    void saveSummoner() {
        //given
        SummonerSaveRequestDto summonerSaveRequestDto = new SummonerSaveRequestDto("name");
        SummonerResponseDto summonerResponseDto = new SummonerResponseDto(987654325L, "encryptedId", "name", "accountId", "puuid", 100L);
        when(summonerService.saveSummoner(any())).thenReturn(summonerResponseDto);
        when(summonerService.findSummoner("name")).thenReturn(summonerResponseDto);

        //when
        EntityExchangeResult<byte[]> entityExchangeResult = saveSummoner(summonerSaveRequestDto);

        //then
        String uri = entityExchangeResult.getResponseHeaders().getLocation().toASCIIString();
        webTestClient.get()
            .uri(uri)
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.id").isNotEmpty()
            .jsonPath("$.name").isEqualTo("name");
    }

    EntityExchangeResult<byte[]> saveSummoner(final SummonerSaveRequestDto requestDto) {
        return webTestClient.post()
            .uri(SummonerController.API_V_1_SUMMONERS)
            .accept(MediaType.APPLICATION_JSON)
            .body(Mono.just(requestDto), SummonerSaveRequestDto.class)
            .exchange()
            .expectStatus().isCreated()
            .expectHeader().valueMatches("Location", SummonerController.API_V_1_SUMMONERS + "/\\S+")
            .expectBody()
            .returnResult();
    }
}