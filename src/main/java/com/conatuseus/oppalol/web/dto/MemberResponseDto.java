package com.conatuseus.oppalol.web.dto;

import com.conatuseus.oppalol.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberResponseDto {

    private String email;
    private String name;

    @Builder
    public MemberResponseDto(final Member member) {
        this.email = member.getEmail();
        this.name = member.getName();
    }
}
