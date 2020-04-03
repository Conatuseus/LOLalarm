package com.conatuseus.oppalol.service.summoner;

import com.conatuseus.oppalol.service.riotservice.RiotService;
import com.conatuseus.oppalol.web.dto.RiotSummonerResponse;
import com.conatuseus.oppalol.web.dto.SummonerResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class SummonerServiceTest {

    @Mock
    private RiotService riotService;

    @InjectMocks
    private SummonerService summonerService;

    @Test
    void findSummoner() {
        //given
        given(riotService.findSummoner("summonerName")).willReturn(
            new RiotSummonerResponse("accountId", 100, 123L, "summonerName", "id", "puuid", 321L));

        //when
        SummonerResponse summonerResponse = summonerService.findSummoner("summonerName");

        //then
        assertThat(summonerResponse.getAccountId()).isEqualTo("accountId");
        assertThat(summonerResponse.getName()).isEqualTo("summonerName");
        assertThat(summonerResponse.getSummonerLevel()).isEqualTo(321L);
    }
}