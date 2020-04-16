package com.conatuseus.oppalol.domain.summoner.exception;

import com.conatuseus.oppalol.global.error.exception.ErrorCode;
import com.conatuseus.oppalol.global.error.exception.InvalidValueException;

public class DuplicatedSummonerException extends InvalidValueException {

    public DuplicatedSummonerException(final String summonerName) {
        super(summonerName, ErrorCode.SUMMONER_DUPLICATION);
    }
}
