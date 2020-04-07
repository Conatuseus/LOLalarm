package com.conatuseus.oppalol.domain.user;

import org.junit.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    private Member member;

    @BeforeEach
    public void setUp() {
        member = Member.builder()
            .email("conatuseus@gmail.com")
            .name("conatuseus")
            .password("abcd1234!")
            .role(Role.MEMBER)
            .build();
    }

    @After
    public void cleanUp() {
        memberRepository.deleteAll();
    }

    @Test
    void save() {
        //when
        memberRepository.save(member);
        List<Member> members = memberRepository.findAll();

        //then
        Member savedMember = members.get(0);
        assertThat(savedMember.getEmail()).isEqualTo(member.getEmail());
        assertThat(savedMember.getName()).isEqualTo(member.getName());
    }

    @Test
    void delete() {
        //given
        Member saved = memberRepository.save(member);
        int savedSize = memberRepository.findAll().size();

        //when
        memberRepository.delete(saved);
        int deletedSize = memberRepository.findAll().size();

        //then
        assertThat(savedSize).isEqualTo(deletedSize + 1);
        assertThat(memberRepository.existsById(saved.getId())).isFalse();
    }
}
