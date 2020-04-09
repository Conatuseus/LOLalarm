package com.conatuseus.oppalol.web;

import com.conatuseus.oppalol.web.dto.MemberSignUpRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @DisplayName("올바른 사용자 가입 요청")
    void saveMember() {
        // given
        MemberSignUpRequestDto memberSignUpRequestDto = new MemberSignUpRequestDto(
            "conatuseus1@gmail.com",
            "p@ssW0rd",
            "p@ssW0rd",
            "사명기"
        );

        //when
        WebTestClient.ResponseSpec responseSpec = saveRequest(memberSignUpRequestDto);

        //then
        responseSpec.expectStatus()
            .isCreated();
    }

    @Test
    @DisplayName("유효하지 않은 이메일의 사용자 가입 요청")
    void saveInvalidEmailMember() {
        // given
        MemberSignUpRequestDto memberSignUpRequestDto = new MemberSignUpRequestDto(
            "conatuseus",
            "p@ssW0rd",
            "p@ssW0rd",
            "사명기"
        );

        // when
        WebTestClient.ResponseSpec responseSpec = saveRequest(memberSignUpRequestDto);

        //then
        responseSpec.expectStatus()
            .isBadRequest();
    }

    @Test
    @DisplayName("중복된 이메일의 사용자 가입 요청")
    void saveDuplicatedEmailMember() {
        // given
        MemberSignUpRequestDto memberSignUpRequestDto = new MemberSignUpRequestDto(
            "conatuseus2@gmail.com",
            "p@ssW0rd",
            "p@ssW0rd",
            "사명기"
        );

        //when
        saveRequest(memberSignUpRequestDto);
        WebTestClient.ResponseSpec responseSpec = saveRequest(memberSignUpRequestDto);

        //then
        responseSpec.expectStatus()
            .isBadRequest();
    }

    @Test
    @DisplayName("일치하지 않은 비밀번호의 사용자 가입 요청")
    void saveNotEqualsPasswordMember() {
        // given
        MemberSignUpRequestDto memberSignUpRequestDto = new MemberSignUpRequestDto(
            "conatuseus3@gmail.com",
            "p@ssW0rd",
            "p@ssW0rd123",
            "사명기"
        );

        //when
        WebTestClient.ResponseSpec responseSpec = saveRequest(memberSignUpRequestDto);

        //then
        responseSpec.expectStatus()
            .isBadRequest();
    }

    @Test
    @DisplayName("유효하지 않은 이름의 사용자 가입 요청")
    void saveInvalidNameMember() {
        // given
        MemberSignUpRequestDto memberSignUpRequestDto = new MemberSignUpRequestDto(
            "conatuseus4@gmail.com",
            "p@ssW0rd",
            "p@ssW0rd",
            "??"
        );

        //when
        WebTestClient.ResponseSpec responseSpec = saveRequest(memberSignUpRequestDto);

        //then
        responseSpec.expectStatus()
            .isBadRequest();
    }

    private WebTestClient.ResponseSpec saveRequest(MemberSignUpRequestDto memberSignUpRequestDto) {
        return webTestClient.post()
            .uri("/api/v1/members")
            .body(Mono.just(memberSignUpRequestDto), MemberSignUpRequestDto.class)
            .exchange();
    }

}