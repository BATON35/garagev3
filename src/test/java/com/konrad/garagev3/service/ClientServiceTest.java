package com.konrad.garagev3.service;

import com.konrad.garagev3.model.dao.Client;
import com.konrad.garagev3.model.dto.ClientDto;
import com.konrad.garagev3.repository.ClientRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.konrad.garagev3.service.ClientServiceTestData.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

    @Mock
    ClientRepository mockClientRepository;

    private ClientService sut;

    @Before
    public void setUp() {
        // initMocks(this);
        sut = new ClientService(mockClientRepository);
        Mockito.when(mockClientRepository.findByEmail(TEST_CLIENT.getEmail())).thenReturn(TEST_CLIENT);
        Mockito.when(mockClientRepository.save(any(Client.class))).thenReturn(TEST_CLIENT);
        Mockito.when(mockClientRepository.findBySurnameAndName(
                TEST_CLIENT.getSurname(), TEST_CLIENT.getName())).thenReturn(TEST_CLIENT);
        Mockito.when(mockClientRepository.findByActiveIs(1)).thenReturn(Arrays.asList(TEST_CLIENT, TEST_CLIENT_2));
//        Mockito.when(mockClientRepository.findByActiveIs(1)).thenReturn(Arrays.asList(
//                TEST_CLIENT,
//                TEST_CLIENT_2));
    }

    @Test
    public void findClientByEmail() {

        final ClientDto result = sut.findClientByEmail(TEST_CLIENT.getEmail());

        Assert.assertEquals(TEST_CLIENT_DTO_EXIST_IN_DATABASE, result);
    }

    @Test
    public void findNonexistentClientByEmail() {

        final ClientDto result = sut.findClientByEmail("nonexistentMail@pl");

        Assert.assertNull(result);

    }

    @Test
    public void saveClient() {
        final ClientDto result = sut.saveClient(TEST_CLIENT_DTO_EXIST_IN_DATABASE);

        Assert.assertEquals(TEST_CLIENT_DTO_EXIST_IN_DATABASE, result);
    }

    @Test
    public void findClientBySurnameAndName() {
        final ClientDto result = sut.findClientBySurnameAndName(
                TEST_CLIENT.getSurname(), TEST_CLIENT.getName());

        Assert.assertEquals(TEST_CLIENT_DTO_EXIST_IN_DATABASE, result);
    }

    @Test
    public void findAllClients() {
        final List result = sut.findAllActiveClients();
        Assert.assertEquals(Arrays.asList(TEST_CLIENT_DTO_2, TEST_CLIENT_DTO_EXIST_IN_DATABASE), result);
    }

    @Test
    public void deactivateClient() {
        Mockito.when(mockClientRepository
                .findById(TEST_ClIENT_ACTIVE_DTO.getId()))
                .thenReturn(Optional.ofNullable(TEST_ClIENT_ACTIVE));

        sut.deactivateClient(TEST_ClIENT_ACTIVE.getId());

        Mockito.verify(mockClientRepository).save(any(Client.class));
    }

//    @Ignore("not yet ready , Please ignore.")
//    @Test
//    public void addVehicleToClient() {
//        Mockito.when(mockClientRepository.save(any(Client.class)))
//                .thenReturn(TEST_CLIENT);
//
//        ClientDto result = sut.addVehicleToClient(TEST_CLIENT_DTO_VEHICLE.getEmail(), TEST_CLIENT_DTO_VEHICLE.getVehicles().get(0));
//
//        Assert.assertEquals(TEST_CLIENT_DTO, result);
//    }
}