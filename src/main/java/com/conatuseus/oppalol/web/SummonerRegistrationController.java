package com.conatuseus.oppalol.web;

import com.conatuseus.oppalol.service.summonerregistrationservice.SummonerRegistrationService;
import com.conatuseus.oppalol.web.dto.SummonerRegistrationRequestDto;
import com.conatuseus.oppalol.web.dto.SummonerRegistrationResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping(SummonerRegistrationController.API_V_1_REGISTRATIONS)
@RestController
public class SummonerRegistrationController {

    public static final String API_V_1_REGISTRATIONS = "/api/v1/registrations";
    private final SummonerRegistrationService summonerRegistrationService;

    @PostMapping
    public ResponseEntity<SummonerRegistrationResponseDto> saveRegistration(@RequestBody final SummonerRegistrationRequestDto requestDto) {
        SummonerRegistrationResponseDto summonerRegistrationResponseDto = summonerRegistrationService.saveSummonerRegistration(requestDto);
        return new ResponseEntity<>(summonerRegistrationResponseDto, HttpStatus.CREATED);
    }
}
