package com.konrad.garagev3.service;

import com.konrad.garagev3.model.dao.Client;
import com.konrad.garagev3.model.dto.ClientDto;
import com.konrad.garagev3.model.dto.VehicleDto;

import java.util.Collections;


public class ClientServiceTestData {
    static final Client TEST_CLIENT = Client.builder()
            .active(1)
            .email("emailTest@pl")
            .name("name")
            .surname("surname")
            .phoneNumber("121212112")
            .vehicles(Collections.emptySet())
            .build();
    static final Client TEST_CLIENT_2 = Client.builder()
            .email("emailTest2@pl")
            .name("name2")
            .surname("surname2")
            .phoneNumber("4444444444")
            .active(1)
            .vehicles(Collections.emptySet())
            .build();
    static final Client TEST_CLIENT_EXIST_IN_DATABASE = Client.builder()
            .id(1L)
            .active(1)
            .email("emailTest@pl")
            .name("name")
            .surname("surname")
            .phoneNumber("121212112")
            .vehicles(Collections.emptySet())
            .build();
    static final Client TEST_CLIENT_TO_SAVE = Client.builder()
            .id(1L)
            .email("toSave@pl")
            .name("nameToSave")
            .surname("toSave")
            .phoneNumber("121212113")
            .active(1)
            .build();
    static final ClientDto TEST_CLIENT_DTO_2 = ClientDto.builder()
            .id(2L)
            .email("emailTest2@pl")
            .name("name2")
            .surname("surname2")
            .phoneNumber("4444444444")
            .active(1)
            //.vehicles(Collections.emptySet())
            .build();
    static final ClientDto TEST_CLIENT_DTO = ClientDto.builder()
            .email("emailTest@pl")
            .name("name")
            .surname("surname")
            .phoneNumber("000000000")
            .active(1)
           // .vehicles(Collections.emptySet())
            .build();
    static final ClientDto TEST_CLIENT_DTO_3 = ClientDto.builder()
            .email("emailTest3@pl")
            .name("name3")
            .surname("surname2")
            .phoneNumber("333333333")
            .active(1)
            .build();
    static final ClientDto TEST_CLIENT_DTO_VEHICLE = ClientDto.builder()
            .email("emailTest@pl")
            .name("name")
            .surname("surname")
            .phoneNumber("000000000")
            .active(1)
            //.vehicles(Collections.singleton(VehicleDto.builder().brand("brand").build()))
            .build();
    static final Client TEST_ClIENT_ACTIVE = Client.builder()
            .id(1L)
            .email("emailTest2@pl")
            .name("name2")
            .surname("surname2")
            .phoneNumber("4444444444")
            .active(1)
            .build();

    static final Client TEST_ClIENT_ACTIVE_DTO = Client.builder()
            .id(1L)
            .email("emailTest2@pl")
            .name("name2")
            .surname("surname2")
            .phoneNumber("4444444444")
            .active(1)
            .build();
}
