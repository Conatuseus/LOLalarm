package com.conatuseus.oppalol.global.error.exception;

public class EntityNotFoundException extends BusinessException {

    public EntityNotFoundException(final String message) {
        super(message, ErrorCode.ENTITY_NOT_FOUND);
    }
}
