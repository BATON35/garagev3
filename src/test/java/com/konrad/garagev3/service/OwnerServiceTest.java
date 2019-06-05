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

import static com.konrad.garagev3.service.OwnerServiceTestData.TEST_OWNER;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class OwnerServiceTest {

    @Autowired
    private ClientService sut;

    @Mock
    ClientRepository clientRepository;
    
    @Before
    public void setUp() {
        initMocks(this);
        Mockito.when(clientRepository.findClientByEmail(TEST_OWNER.getEmail())).thenReturn(TEST_OWNER);
        Mockito.when(clientRepository.save(TEST_OWNER)).thenReturn(TEST_OWNER);

    }

    @Test
    public void findOwnerByEmail() {

        final Client result = clientRepository.findClientByEmail(TEST_OWNER.getEmail());

        Assert.assertEquals(TEST_OWNER, result);

    }

    @Test
    public void findNonexistentOwnerByEmail() {

        final Client result = clientRepository.findClientByEmail("nonexistentMail@pl");

        Assert.assertNull(result);

    }
    @Test
    public void saveOwner() {
        final Client result = clientRepository.save(TEST_OWNER);

        Assert.assertEquals(TEST_OWNER, result);
    }

}