package com.konrad.garagev3.service;

import com.konrad.garagev3.model.dao.Owner;
import com.konrad.garagev3.repository.OwnerRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(MockitoJUnitRunner.class)
public class OwnerServiceTest {
    @Autowired
    private OwnerService sut;

    @Mock
    OwnerRepository ownerRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findOwnerByEmail() {
        Owner owner1 = Owner.builder().id(3).email("afds").name("af").phoneNumber("a4123").build();
        Mockito.when(ownerRepository.findOwnerByEmail(Mockito.anyString())).thenReturn(
                owner1);



        Assert.assertEquals(Owner.builder().email("test").build(), sut.findOwnerByEmail("test"));


    }

}