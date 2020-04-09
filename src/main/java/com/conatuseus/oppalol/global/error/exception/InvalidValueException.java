package com.conatuseus.oppalol.global.error.exception;

public class InvalidValueException extends BusinessException {

    public InvalidValueException(final String value) {
        super(value, ErrorCode.INVALID_INPUT_VALUE);
    }

    public InvalidValueException(final String value, final ErrorCode errorCode) {
        super(value, errorCode);
    }
}
