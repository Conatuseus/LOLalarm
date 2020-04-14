package com.conatuseus.oppalol.service.summonerregistrationservice;

import com.conatuseus.oppalol.domain.summonerregistration.SummonerRegistration;
import com.conatuseus.oppalol.web.dto.SummonerRegistrationRequestDto;
import com.conatuseus.oppalol.web.dto.SummonerRegistrationResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class SummonerRegistrationService {

    private final SummonerRegistrationInternalService summonerRegistrationInternalService;

    public SummonerRegistrationResponseDto saveSummonerRegistration(final SummonerRegistrationRequestDto requestDto) {
        SummonerRegistration summonerRegistration = summonerRegistrationInternalService.saveSummonerRegistration(requestDto);
        return new SummonerRegistrationResponseDto(summonerRegistration);
    }
}
