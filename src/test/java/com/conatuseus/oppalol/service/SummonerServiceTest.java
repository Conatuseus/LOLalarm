package com.conatuseus.oppalol.service;

import com.conatuseus.oppalol.domain.summoner.Summoner;
import com.conatuseus.oppalol.domain.summoner.exception.DuplicatedSummonerException;
import com.conatuseus.oppalol.service.riotservice.RiotService;
import com.conatuseus.oppalol.service.summonerservice.SummonerInternalService;
import com.conatuseus.oppalol.service.summonerservice.SummonerService;
import com.conatuseus.oppalol.web.dto.RiotSummonerResponseDto;
import com.conatuseus.oppalol.web.dto.SummonerResponseDto;
import com.conatuseus.oppalol.web.dto.SummonerSaveRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SummonerServiceTest {

    @Mock
    private RiotService riotService;

    @Mock
    private SummonerInternalService summonerInternalService;

    @InjectMocks
    private SummonerService summonerService;

    @Test
    @DisplayName("Summoner 존재하지 않아 라이엇API 사용 검색")
    void findSummonerByRiotService() {
        //given
        given(riotService.findSummoner("summonerName")).willReturn(
            new RiotSummonerResponseDto("accountId", 100, 123L, "summonerName", "id", "puuid", 321L));

        given(summonerInternalService.existsByName(any())).willReturn(false);

        //when
        SummonerResponseDto summonerResponseDto = summonerService.findSummoner("summonerName");

        //then
        assertThat(summonerResponseDto.getAccountId()).isEqualTo("accountId");
        assertThat(summonerResponseDto.getName()).isEqualTo("summonerName");
        assertThat(summonerResponseDto.getSummonerLevel()).isEqualTo(321L);
    }

    @Test
    @DisplayName("저장되어 있는 Summoner 검색")
    void findSummonerBySummonerInternalService() {
        //given
        given(summonerInternalService.findByName("summonerName")).willReturn(
            new Summoner("encryptedId", "summonerName", "accountId", "puuid", 321L, 321));

        given(summonerInternalService.existsByName(any())).willReturn(true);

        //when
        SummonerResponseDto summonerResponseDto = summonerService.findSummoner("summonerName");

        //then
        assertThat(summonerResponseDto.getAccountId()).isEqualTo("accountId");
        assertThat(summonerResponseDto.getName()).isEqualTo("summonerName");
        assertThat(summonerResponseDto.getSummonerLevel()).isEqualTo(321L);
    }

    @Test
    @DisplayName("정상적 Summoner 저장")
    void saveSummoner() {
        //given
        Summoner summoner = Summoner.builder()
            .name("summonerName")
            .accountId("accountId")
            .encryptedId("encryptedId")
            .profileIconId(3221)
            .summonerLevel(321L)
            .puuId("puuid")
            .build();
        SummonerSaveRequestDto requestDto = new SummonerSaveRequestDto("summonerName");

        when(summonerInternalService.saveSummoner(any())).thenReturn(summoner);
        when(riotService.findSummoner(any())).thenReturn(new RiotSummonerResponseDto(
            summoner.getAccountId(), summoner.getProfileIconId(), 0L, summoner.getName(), summoner.getAccountId(), summoner.getPuuId(), summoner.getSummonerLevel()));

        //when
        SummonerResponseDto summonerResponseDto = summonerService.saveSummoner(requestDto);

        //then
        assertThat(summoner.getName()).isEqualTo(summonerResponseDto.getName());
        assertThat(summoner.getAccountId()).isEqualTo(summonerResponseDto.getAccountId());
    }

    @Test
    @DisplayName("이미 존재하는 Summoner 저장 요청")
    void saveAlreadyExistsSummoner() {
        //given
        Summoner summoner = Summoner.builder()
            .name("summonerName")
            .accountId("accountId")
            .encryptedId("encryptedId")
            .profileIconId(3221)
            .summonerLevel(321L)
            .puuId("puuid")
            .build();
        SummonerSaveRequestDto requestDto = new SummonerSaveRequestDto("summonerName");

        when(riotService.findSummoner(any())).thenReturn(new RiotSummonerResponseDto(
            summoner.getAccountId(), summoner.getProfileIconId(), 0L, summoner.getName(), summoner.getAccountId(), summoner.getPuuId(), summoner.getSummonerLevel()));
        when(summonerInternalService.saveSummoner(any())).thenThrow(DuplicatedSummonerException.class);

        //when & then
        assertThrows(DuplicatedSummonerException.class, () -> summonerService.saveSummoner(requestDto));
    }
}