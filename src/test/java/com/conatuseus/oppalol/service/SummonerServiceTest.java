package com.conatuseus.oppalol.service;

import com.conatuseus.oppalol.web.dto.RiotSummonerResponseDto;
import com.conatuseus.oppalol.web.dto.SummonerResponseDto;
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
            new RiotSummonerResponseDto("accountId", 100, 123L, "summonerName", "id", "puuid", 321L));

        //when
        SummonerResponseDto summonerResponseDto = summonerService.findSummoner("summonerName");

        //then
        assertThat(summonerResponseDto.getAccountId()).isEqualTo("accountId");
        assertThat(summonerResponseDto.getName()).isEqualTo("summonerName");
        assertThat(summonerResponseDto.getSummonerLevel()).isEqualTo(321L);
    }
}