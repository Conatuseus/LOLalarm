package com.conatuseus.oppalol.domain.member.exception;

import com.conatuseus.oppalol.global.error.exception.ErrorCode;
import com.conatuseus.oppalol.global.error.exception.InvalidValueException;

public class DuplicatedEmailException extends InvalidValueException {

    public DuplicatedEmailException(final String email) {
        super(email, ErrorCode.EMAIL_DUPLICATION);
    }
}