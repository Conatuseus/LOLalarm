package com.conatuseus.oppalol.service;

import com.conatuseus.oppalol.domain.member.Member;
import com.conatuseus.oppalol.domain.member.MemberRepository;
import com.conatuseus.oppalol.domain.member.Role;
import com.conatuseus.oppalol.domain.member.exception.DuplicatedEmailException;
import com.conatuseus.oppalol.web.dto.MemberResponseDto;
import com.conatuseus.oppalol.web.dto.MemberSignUpRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private MemberService memberService;

    private Member member;

    @BeforeEach
    void setUp() {
        member = Member.builder()
            .email("conatuseus@gmail.com")
            .name("사명기")
            .password("p@ssW0rd")
            .role(Role.MEMBER)
            .build();
    }

    @Test
    void saveMember() {
        //given
        String email = "conatuseus@gmail.com";
        String name = "사명기";
        String password = "p@ssW0rd";
        String confirmPassword = "p@ssW0rd";

        MemberSignUpRequestDto requestDto = new MemberSignUpRequestDto(email, password, confirmPassword, name);

        when(bCryptPasswordEncoder.encode(any())).thenReturn("%1%2%3%a%b");
        when(memberRepository.existsMemberByEmail(email)).thenReturn(false);
        when(memberRepository.save(any())).thenReturn(member);

        //when
        MemberResponseDto memberResponseDto = memberService.saveMember(requestDto);

        //then
        assertThat(memberResponseDto).isNotNull();
        assertThat(memberResponseDto.getEmail()).isEqualTo("conatuseus@gmail.com");
        assertThat(memberResponseDto.getName()).isEqualTo("사명기");
    }

    @Test
    void saveDuplicatedEmailMember() {
        //given
        String email = "conatuseus@gmail.com";
        String name = "사명기";
        String password = "p@ssW0rd";
        String confirmPassword = "p@ssW0rd";

        MemberSignUpRequestDto requestDto = new MemberSignUpRequestDto(email, password, confirmPassword, name);

        when(memberRepository.existsMemberByEmail(email)).thenReturn(true);

        //when
        assertThrows(DuplicatedEmailException.class, () -> memberService.saveMember(requestDto));
    }
}