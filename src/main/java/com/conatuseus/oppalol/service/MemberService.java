package com.conatuseus.oppalol.service;

import com.conatuseus.oppalol.domain.member.Member;
import com.conatuseus.oppalol.domain.member.MemberRepository;
import com.conatuseus.oppalol.domain.member.exception.DuplicatedEmailException;
import com.conatuseus.oppalol.web.dto.MemberResponseDto;
import com.conatuseus.oppalol.web.dto.MemberSignUpRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public MemberResponseDto saveMember(final MemberSignUpRequestDto requestDto) {
        if (memberRepository.existsMemberByEmail(requestDto.getEmail())) {
            throw new DuplicatedEmailException(requestDto.getEmail());
        }

        String encodedPassword = bCryptPasswordEncoder.encode(requestDto.getPassword());
        Member savedMember = memberRepository.save(requestDto.toEntity(encodedPassword));
        return new MemberResponseDto(savedMember);
    }
}
