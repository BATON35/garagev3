package com.konrad.garagev3.service;

import com.konrad.garagev3.model.dao.Client;
import com.konrad.garagev3.model.dto.ClientDto;

public class ClientServiceTestData {
    static final Client TEST_CLIENT = Client.builder()
            .email("emailTest@pl")
            .name("name")
            .surname("surname")
            .phoneNumber("121212112")
            .build();
    static final Client TEST_CLIENT_2 = Client.builder()
            .email("emailTest2@pl")
            .name("name2")
            .surname("surname2")
            .phoneNumber("4444444444")
            .build();
    static final ClientDto TEST_CLIENT_DTO = ClientDto.builder()
            .email("emailTest@pl")
            .name("name")
            .surname("surname")
            .phoneNumber("121212112")
            .build();
    static final ClientDto TEST_CLIENT_DTO_2 = ClientDto.builder()
            .email("emailTest2@pl")
            .name("name2")
            .surname("surname2")
            .phoneNumber("4444444444")
            .build();
}
