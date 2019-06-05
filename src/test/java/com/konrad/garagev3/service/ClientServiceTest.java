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
import org.springframework.beans.factory.annotation.Autowired;

import static com.konrad.garagev3.service.ClientServiceTestData.TEST_CLIENT;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

    @Autowired
    private ClientService sut;

    @Mock
    ClientRepository clientRepository;
    
    @Before
    public void setUp() {
        initMocks(this);
        Mockito.when(clientRepository.findClientByEmail(TEST_CLIENT.getEmail())).thenReturn(TEST_CLIENT);
        Mockito.when(clientRepository.save(TEST_CLIENT)).thenReturn(TEST_CLIENT);
        Mockito.when(clientRepository.findClientBySurnameAndName(
                TEST_CLIENT.getSurname(), TEST_CLIENT.getName())).thenReturn(TEST_CLIENT);

    }

    @Test
    public void findOwnerByEmail() {

        final Client result = clientRepository.findClientByEmail(TEST_CLIENT.getEmail());

        Assert.assertEquals(TEST_CLIENT, result);

    }

    @Test
    public void findNonexistentOwnerByEmail() {

        final Client result = clientRepository.findClientByEmail("nonexistentMail@pl");

        Assert.assertNull(result);

    }
    @Test
    public void saveOwner() {
        final Client result = clientRepository.save(TEST_CLIENT);

        Assert.assertEquals(TEST_CLIENT, result);
    }

    @Test
    public void findClientBySurnameAndName() {
        final Client result = clientRepository.findClientBySurnameAndName(
                TEST_CLIENT.getSurname(), TEST_CLIENT.getName());

        Assert.assertEquals(TEST_CLIENT, result);
    }
}