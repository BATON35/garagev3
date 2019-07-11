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

import static com.konrad.garagev3.service.ClientServiceTestData.TEST_CLIENT_DTO;

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
        sut.saveClient(TEST_CLIENT_DTO);
    }

    @Test
    public void clientNotExist() {
        Assert.assertNull(sut.findClientByEmail("mailNotEsist@pl"));
    }

    @Test
    public void clientExist() {
        ClientDto result = sut.findClientByEmail(TEST_CLIENT_DTO.getEmail());
        Assert.assertEquals(TEST_CLIENT_DTO, result);
    }

    @Test
    public void saveUser() {
        ClientDto result = sut.saveClient(TEST_CLIENT_DTO);
    }
}
