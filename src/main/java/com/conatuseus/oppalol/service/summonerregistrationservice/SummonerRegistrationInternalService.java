package com.conatuseus.oppalol.service.summonerregistrationservice;

import com.conatuseus.oppalol.domain.member.Member;
import com.conatuseus.oppalol.domain.summoner.Summoner;
import com.conatuseus.oppalol.domain.summonerregistration.SummonerRegistration;
import com.conatuseus.oppalol.domain.summonerregistration.SummonerRegistrationRepository;
import com.conatuseus.oppalol.service.memberservice.MemberInternalService;
import com.conatuseus.oppalol.service.riotservice.RiotService;
import com.conatuseus.oppalol.service.summonerservice.SummonerInternalService;
import com.conatuseus.oppalol.web.dto.RiotSummonerResponseDto;
import com.conatuseus.oppalol.web.dto.SummonerRegistrationRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class SummonerRegistrationInternalService {

    private final RiotService riotService;
    private final SummonerInternalService summonerInternalService;
    private final MemberInternalService memberInternalService;
    private final SummonerRegistrationRepository summonerRegistrationRepository;

    public SummonerRegistration saveSummonerRegistration(final SummonerRegistrationRequestDto requestDto) {
        Member member = memberInternalService.findById(requestDto.getMemberId());

        if (summonerInternalService.existsByName(requestDto.getSummonerName())) {
            Summoner foundSummoner = summonerInternalService.findByName(requestDto.getSummonerName());
            return summonerRegistrationRepository.save(new SummonerRegistration(member, foundSummoner));
        }
        RiotSummonerResponseDto riotSummoner = riotService.findSummoner(requestDto.getSummonerName());
        Summoner savedSummoner = summonerInternalService.save(riotSummoner.toEntity());
        return summonerRegistrationRepository.save(new SummonerRegistration(member, savedSummoner));
    }
}
