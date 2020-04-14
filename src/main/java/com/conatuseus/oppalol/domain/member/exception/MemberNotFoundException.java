package com.conatuseus.oppalol.domain.member.exception;

import com.conatuseus.oppalol.global.error.exception.EntityNotFoundException;

public class MemberNotFoundException extends EntityNotFoundException {

    public MemberNotFoundException(final Long target) {
        super(target + " is not found");
    }
}
