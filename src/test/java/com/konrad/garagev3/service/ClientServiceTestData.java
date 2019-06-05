package com.konrad.garagev3.service;

import com.konrad.garagev3.model.dao.Client;

public class ClientServiceTestData {
    static final Client TEST_CLIENT = Client.builder()
            .email("emailTest@pl")
            .name("name")
            .surname("surname")
            .phoneNumber("121212112")
            .build();
}
