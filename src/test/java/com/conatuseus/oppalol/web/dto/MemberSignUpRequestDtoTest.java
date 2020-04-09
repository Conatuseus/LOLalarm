package com.conatuseus.oppalol.web.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

class MemberSignUpRequestDtoTest {

    private Validator validator;
    private String validEmail;
    private String validName;
    private String validPassword;
    private String validConfirmPassword;

    @BeforeEach
    void setUp() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();

        validEmail = "conatuseus@gmail.com";
        validName = "conatuseus";
        validPassword = "p@ssW0rd";
        validConfirmPassword = "p@ssW0rd";
    }

    @Test
    @DisplayName("유요한 사용자 테스트")
    void validMemberTest() {
        //when
        MemberSignUpRequestDto memberSignUpRequestDto = new MemberSignUpRequestDto(validEmail, validPassword, validConfirmPassword, validName);
        Set<ConstraintViolation<MemberSignUpRequestDto>> violations = validator.validate(memberSignUpRequestDto);

        //then
        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("유효하지 않은 이메일 테스트")
    void invalidEmailTest() {
        //given
        String invalidEmail = "invalidEmail";

        //when
        MemberSignUpRequestDto memberSignUpRequestDto = new MemberSignUpRequestDto(invalidEmail, validPassword, validConfirmPassword, validName);
        Set<ConstraintViolation<MemberSignUpRequestDto>> violations = validator.validate(memberSignUpRequestDto);

        //then
        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("공백 이메일 테스트")
    void blankEmailTest() {
        //given
        String blankEmail = "";

        //when
        MemberSignUpRequestDto memberSignUpRequestDto = new MemberSignUpRequestDto(blankEmail, validPassword, validConfirmPassword, validName);
        Set<ConstraintViolation<MemberSignUpRequestDto>> violations = validator.validate(memberSignUpRequestDto);

        //then
        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("유효하지 않은 이름 테스트")
    void invalidNameTest() {
        //given
        String invalidName = "사명기!!!";

        //when
        MemberSignUpRequestDto memberSignUpRequestDto = new MemberSignUpRequestDto(validEmail, validPassword, validConfirmPassword, invalidName);
        Set<ConstraintViolation<MemberSignUpRequestDto>> violations = validator.validate(memberSignUpRequestDto);

        //then
        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("공백 이름 테스트")
    void blankNameTest() {
        //given
        String blankName = "";

        //when
        MemberSignUpRequestDto memberSignUpRequestDto = new MemberSignUpRequestDto(validEmail, validPassword, validConfirmPassword, blankName);
        Set<ConstraintViolation<MemberSignUpRequestDto>> violations = validator.validate(memberSignUpRequestDto);

        //then
        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("서로 다른 비밀번호 테스트")
    void differentPasswordTest() {
        //given
        String differentConfirmPassword = "p@ssW0rd123";

        //when
        MemberSignUpRequestDto memberSignUpRequestDto = new MemberSignUpRequestDto(validEmail, validPassword, differentConfirmPassword, validName);
        Set<ConstraintViolation<MemberSignUpRequestDto>> violations = validator.validate(memberSignUpRequestDto);

        //then
        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("유효하지 않은 비밀번호 테스트")
    void invalidPasswordTest() {
        //given
        String invalidPassword = "password";
        String invalidConfirmPassword = "password";

        //when
        MemberSignUpRequestDto memberSignUpRequestDto = new MemberSignUpRequestDto(validEmail, invalidPassword, invalidConfirmPassword, validName);
        Set<ConstraintViolation<MemberSignUpRequestDto>> violations = validator.validate(memberSignUpRequestDto);

        //then
        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("공백 비밀번호 테스트")
    void blankPasswordTest() {
        //given
        String invalidPassword = "";
        String invalidConfirmPassword = "";

        //when
        MemberSignUpRequestDto memberSignUpRequestDto = new MemberSignUpRequestDto(validEmail, invalidPassword, invalidConfirmPassword, validName);
        Set<ConstraintViolation<MemberSignUpRequestDto>> violations = validator.validate(memberSignUpRequestDto);

        //then
        assertFalse(violations.isEmpty());
    }
}