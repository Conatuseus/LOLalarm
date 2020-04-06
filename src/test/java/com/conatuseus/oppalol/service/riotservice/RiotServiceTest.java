package com.conatuseus.oppalol.service.riotservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

@RunWith(SpringRunner.class)
@RestClientTest(RiotService.class)
class RiotServiceTest {

    @Autowired
    private MockRestServiceServer mockServer;

    @Autowired
    private RiotService riotService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void findSummoner() throws JsonProcessingException {
        //given

    }
}