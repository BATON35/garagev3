package com.konrad.garagev3.service;

import com.konrad.garagev3.model.dao.Client;

public class OwnerServiceTestData {
    static final Client TEST_OWNER = Client.builder()
            .email("ownerTest@pl")
            .name("owner")
            .phoneNumber("121212112")
            .build();
}
