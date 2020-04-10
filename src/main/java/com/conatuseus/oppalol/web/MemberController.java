package com.conatuseus.oppalol.web;

import com.conatuseus.oppalol.service.MemberService;
import com.conatuseus.oppalol.web.dto.MemberResponseDto;
import com.conatuseus.oppalol.web.dto.MemberSignUpRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(MemberController.API_V_1_MEMBERS)
@RequiredArgsConstructor
public class MemberController {

    public static final String API_V_1_MEMBERS = "/api/v1/members";
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberResponseDto> saveMember(@RequestBody @Valid MemberSignUpRequestDto memberSignUpRequest) {
        MemberResponseDto memberResponseDto = memberService.saveMember(memberSignUpRequest);
        return new ResponseEntity<>(memberResponseDto, HttpStatus.CREATED);
    }
}
