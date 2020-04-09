package com.conatuseus.oppalol.web;

import com.conatuseus.oppalol.service.SummonerService;
import com.conatuseus.oppalol.web.dto.SummonerResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@AutoConfigureMockMvc
class SummonerControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private SummonerService summonerService;

    @Test
    void findSummoner() {
        //given
        SummonerResponseDto summonerResponseDto = new SummonerResponseDto("encryptedId", "name", "accountId", "puuid", 100L);

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
}