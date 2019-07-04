package com.konrad.garagev3.service;

import com.konrad.garagev3.model.dao.Client;
import com.konrad.garagev3.repository.ClientRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static com.konrad.garagev3.service.ClientServiceTestData.TEST_CLIENT;
import static com.konrad.garagev3.service.ClientServiceTestData.TEST_CLIENT_DTO;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

    @Mock
    ClientRepository MockClientRepository;

    private ClientService sut;

    @Before
    public void setUp() {
        // initMocks(this);
        sut = new ClientService(MockClientRepository);
        Mockito.when(MockClientRepository.findClientByEmail(TEST_CLIENT.getEmail())).thenReturn(TEST_CLIENT);
        Mockito.when(MockClientRepository.save(TEST_CLIENT)).thenReturn(TEST_CLIENT);
        Mockito.when(MockClientRepository.findClientBySurnameAndName(
                TEST_CLIENT.getSurname(), TEST_CLIENT.getName())).thenReturn(TEST_CLIENT);

    }

    @Test
    public void findClientByEmail() {

        final Client result = sut.findClientByEmail(TEST_CLIENT.getEmail());

        Assert.assertEquals(TEST_CLIENT, result);

    }

    @Test
    public void findNonexistentClientByEmail() {

        final Client result = sut.findClientByEmail("nonexistentMail@pl");

        Assert.assertNull(result);

    }

    @Test
    public void saveClient() {
        final Client result = sut.saveClient(TEST_CLIENT_DTO);

        Assert.assertEquals(TEST_CLIENT, result);
    }

    @Test
    public void findClientBySurnameAndName() {
        final Client result = sut.findClientBySurnameAndName(
                TEST_CLIENT.getSurname(), TEST_CLIENT.getName());

        Assert.assertEquals(TEST_CLIENT, result);
    }
}