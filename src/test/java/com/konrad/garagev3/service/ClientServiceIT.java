package com.konrad.garagev3.service;

import com.konrad.garagev3.model.dto.ClientDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.MySQLContainer;

import java.util.Arrays;
import java.util.List;

import static com.konrad.garagev3.service.ClientServiceTestData.*;
import static java.util.Objects.isNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(initializers = ClientServiceIT.Initializer.class)
public class ClientServiceIT {

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of("spring.datasource.url = jdbc:mysql://"
                            + mysql.getContainerIpAddress() + ":"
                            + mysql.getMappedPort(3306) + "/"
                            + mysql.getDatabaseName(),
                    "spring.datasource.username = " + mysql.getUsername(),
                    "spring.datasource.password = " + mysql.getPassword()).applyTo(configurableApplicationContext);
        }
    }

    @Autowired
    ClientService sut;

    @ClassRule
    public static MySQLContainer mysql = new MySQLContainer<>("mysql:5.7")
            .withExposedPorts(3306);

    @Before
    public void fillDatabase() {
        if (isNull(sut.findClientByEmail(TEST_CLIENT_DTO_EXIST_IN_DATABASE.getEmail()))) {
            sut.saveClient(TEST_CLIENT_DTO_EXIST_IN_DATABASE);
        }
    }

    @Test
    public void addVehicleToClient() {
    }

    @Test
    public void clientNotExist() {
        Assert.assertNull(sut.findClientByEmail("mailNotEsist@pl"));
    }

    @Test
    public void findExistClientByEmail() {
        ClientDto result = sut.findClientByEmail(TEST_CLIENT_DTO_EXIST_IN_DATABASE.getEmail());
        Assert.assertEquals(TEST_CLIENT_DTO_EXIST_IN_DATABASE, result);
    }

    @Test
    public void saveClient() {
        ClientDto result = sut.saveClient(TEST_CLIENT_DTO_TO_SAVE);
        Assert.assertEquals(TEST_CLIENT_DTO_TO_SAVE, result);

        sut.deleteClient(TEST_CLIENT_DTO_TO_SAVE.getEmail());
    }
    @Test
    public void deleteClient() {
        sut.deleteClient(TEST_CLIENT_DTO_EXIST_IN_DATABASE.getEmail());
        Assert.assertNull(sut.findClientByEmail(TEST_CLIENT_DTO_EXIST_IN_DATABASE.getEmail()));
    }

    @Test
    public void findClientBySurnameAndName() {
        String surname = TEST_CLIENT_DTO_EXIST_IN_DATABASE.getSurname();
        String name = TEST_CLIENT_DTO_EXIST_IN_DATABASE.getName();
        ClientDto result = sut.findClientBySurnameAndName(surname,name);
        Assert.assertEquals(TEST_CLIENT_DTO_EXIST_IN_DATABASE, result);

    }

    @Test
    public void findAllActiveClients() {
        sut.deleteClient(TEST_CLIENT_DTO_EXIST_IN_DATABASE.getEmail());
        sut.deleteClient(TEST_CLIENT_DTO_TO_SAVE.getEmail());

        sut.saveClient(TEST_CLIENT_DTO);
        sut.saveClient(TEST_CLIENT_DTO_2);

        List<ClientDto> result = sut.findAllActiveClients();

        Assert.assertEquals(Arrays.asList(TEST_CLIENT_DTO_2 ,TEST_CLIENT_DTO), result);

        sut.deleteClient(TEST_CLIENT_DTO.getEmail());
        sut.deleteClient(TEST_CLIENT_DTO_2.getEmail());
    }

    @Test
    public void deactivateClient() {

        sut.deactivateClient(TEST_CLIENT_DTO_EXIST_IN_DATABASE.getEmail());

        int result = sut.findClientByEmail(TEST_CLIENT_DTO_EXIST_IN_DATABASE.getEmail()).getActive();

        Assert.assertEquals(0, result);

        sut.deleteClient(TEST_CLIENT_DTO_EXIST_IN_DATABASE.getEmail());
    }
}
