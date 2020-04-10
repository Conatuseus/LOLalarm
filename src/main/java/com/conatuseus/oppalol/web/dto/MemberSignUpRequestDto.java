package com.conatuseus.oppalol.web.dto;

import com.conatuseus.oppalol.domain.member.Member;
import com.conatuseus.oppalol.domain.member.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberSignUpRequestDto {

    public static final String PASSWORD_NOT_EQUALS_MESSAGE = "비밀번호 입력이 일치하지 않습니다.";
    public static final String BLANK_EMAIL_MESSAGE = "이메일을 입력해 주세요.";
    public static final String INVALID_EMAIL_MESSAGE = "유효한 이메일을 입력해 주세요.";
    public static final String BLANK_PASSWORD_MESSAGE = "비밀번호를 입력해 주세요.";
    public static final String BLANK_NAME_MESSAGE = "이름을 입력해 주세요.";
    public static final String INVALID_PASSWORD_PATTERN_MESSAGE = "비밀번호 형식이 잘못되었습니다.";

    public static final String PASSWORD_PATTERN = "^.*(?=^.{8,}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$";
    public static final String NAME_PATTERN = "^([A-Za-z가-힣]{2,16})$";

    @NotBlank(message = BLANK_EMAIL_MESSAGE)
    @Email(message = INVALID_EMAIL_MESSAGE)
    private String email;

    @NotBlank(message = BLANK_PASSWORD_MESSAGE)
    @Pattern(regexp = PASSWORD_PATTERN, message = INVALID_PASSWORD_PATTERN_MESSAGE)
    private String password;

    @NotBlank(message = BLANK_PASSWORD_MESSAGE)
    @Pattern(regexp = PASSWORD_PATTERN, message = INVALID_PASSWORD_PATTERN_MESSAGE)
    private String confirmPassword;

    @NotBlank(message = BLANK_NAME_MESSAGE)
    @Pattern(regexp = NAME_PATTERN)
    private String name;

    @AssertTrue(message = PASSWORD_NOT_EQUALS_MESSAGE)
    public boolean isPasswordEquals() {
        return password.equals(confirmPassword);
    }

    public Member toEntity(final String encodedPassword) {
        return Member.builder()
            .email(email)
            .name(name)
            .password(encodedPassword)
            .role(Role.MEMBER)
            .build();
    }
}
