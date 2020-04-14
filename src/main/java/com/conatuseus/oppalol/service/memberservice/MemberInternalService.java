package com.conatuseus.oppalol.service.memberservice;

import com.conatuseus.oppalol.domain.member.Member;
import com.conatuseus.oppalol.domain.member.MemberRepository;
import com.conatuseus.oppalol.domain.member.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class MemberInternalService {

    private final MemberRepository memberRepository;

    public Member save(final Member member) {
        return memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public Member findById(final Long id) {
        return memberRepository.findById(id)
            .orElseThrow(() -> new MemberNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public boolean existsMemberByEmail(final String email) {
        return memberRepository.existsMemberByEmail(email);
    }
}
