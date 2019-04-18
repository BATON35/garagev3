package com.konrad.garagev3.service;

import com.konrad.garagev3.model.dao.Owner;
import com.konrad.garagev3.repository.OwnerRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

@RunWith(MockitoJUnitRunner.class)
public class OwnerServiceTest {

    @Autowired
    private OwnerService sut;

    @MockBean
    private OwnerRepository ownerRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findOwnerByEmail() {
        Mockito.when(ownerRepository.findOwnerByEmail(Mockito.anyString())).thenReturn(
                Owner.builder().email("test").build());

        Owner owner = sut.findOwnerByEmail("test");

        Assert.assertEquals(Owner.builder().email("test").build(), owner);


    }
}