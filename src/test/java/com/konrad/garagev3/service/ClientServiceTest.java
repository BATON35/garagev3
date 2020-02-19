package com.konrad.garagev3.service;

import com.konrad.garagev3.model.dao.Client;
import com.konrad.garagev3.model.dto.ClientDto;
import com.konrad.garagev3.repository.ClientRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;
import java.util.*;

import static com.konrad.garagev3.service.ClientServiceTestData.*;
import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

    @Mock
    ClientRepository mockClientRepository;

    private ClientService sut;
//
//    @Before
//    public void setUp() {
//        // initMocks(this);
//        sut = new ClientService(mockClientRepository, );
//        Mockito.when(mockClientRepository.findByEmail(TEST_CLIENT.getEmail())).thenReturn(TEST_CLIENT);
//        Mockito.when(mockClientRepository.save(any(Client.class))).thenReturn(TEST_CLIENT);
//        Mockito.when(mockClientRepository.findBySurnameAndName(
//                TEST_CLIENT.getSurname(), TEST_CLIENT.getName())).thenReturn(TEST_CLIENT);
//        Mockito.when(mockClientRepository.findByActiveIs(1)).thenReturn(Arrays.asList(TEST_CLIENT, TEST_CLIENT_2));
//        Mockito.when(mockClientRepository.findById(5L)).thenReturn(Optional.of(Client
//                .builder()
//                .name("name")
//                .id(1L)
//                .active(1)
//                .surname("surname")
//                .email("email@pl")
//                .phoneNumber("121212112")
//                .vehicles(Collections.emptySet())
//                .build()));
//        Mockito.when(mockClientRepository.findById(10L)).thenReturn(Optional.empty());
////        Mockito.when(mockClientRepository.findByActiveIs(1)).thenReturn(Arrays.asList(
////                TEST_CLIENT,
////                TEST_CLIENT_2));
//    }

    @Test
    public void findClientByEmail() {

        final ClientDto result = sut.findClientByEmail(TEST_CLIENT.getEmail());

        assertThat(TEST_CLIENT_DTO).isEqualTo(result);
    }

    @Test
    public void findNonexistentClientByEmail() {

        final ClientDto result = sut.findClientByEmail("nonexistentMail@pl");

        assertThat(result).isNull();

    }

    @Test
    public void saveClient() {
        final Client result = sut.saveClient(TEST_CLIENT_EXIST_IN_DATABASE);

        assertThat(TEST_CLIENT_EXIST_IN_DATABASE).isEqualTo(result);
    }

    @Test
    public void findClientBySurnameAndName() {
        final ClientDto result = sut.findClientBySurnameAndName(
                TEST_CLIENT.getSurname(), TEST_CLIENT.getName());

        assertThat(TEST_CLIENT_DTO).isEqualTo(result);
    }

    @Test
    public void shouldReturnAllActivateClients()  {
        final List<ClientDto> result = sut.findAllActiveClients();
        assertThat(result).containsOnly(TEST_CLIENT_DTO_2, TEST_CLIENT_DTO)
                .isSortedAccordingTo(Comparator.comparing(ClientDto::getEmail));
    }

    @Test
    public void deactivateClient() {
        Mockito.when(mockClientRepository
                .findById(TEST_ClIENT_ACTIVE_DTO.getId()))
                .thenReturn(Optional.ofNullable(TEST_ClIENT_ACTIVE));

        sut.deactivateClient(TEST_ClIENT_ACTIVE.getId());

        Mockito.verify(mockClientRepository).save(any(Client.class));
    }

    @Test
    public void shouldNotDeactivateClient() {
        assertThatThrownBy(() -> sut.deactivateClient(43L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Client with 43 doesn't exist");
    }

    @Test
    public void shouldGetClientDtoById() {
        ClientDto result = sut.findById(5L);

        assertThat(result).isEqualTo(TEST_CLIENT_DTO);
    }

    @Test
    public void shouldNotFindClientById() {
        assertThatThrownBy(() -> sut.findById(10L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Client with 10 doesn't exist");

    }

    @Test
    public void shouldNotEditClient() {
        assertThatThrownBy(() -> sut.editClient(ClientDto
                .builder()
                .id(11L)
                .build()))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Client with 11 doesn't exist");
    }

    @Test
    public void shouldEditClient() {
        ClientDto result = sut.editClient(ClientDto.builder().id(5L).email("email@pl").build());

        assertThat(result).isEqualTo(ClientDto
                .builder()
                .name("name")
                .id(1L)
                .active(1)
                .surname("surname")
                .email("email@pl")
                .phoneNumber("121212112")
                .vehicles(Collections.emptySet())
                .build());
    }

    @Test
    public void shouldNotChangeClient() {
        ClientDto result = sut.editClient(ClientDto.builder().id(5L).build());

        assertThat(result).isEqualTo(ClientDto
                .builder()
                .name("name")
                .id(1L)
                .active(1)
                .surname("surname")
                .email("email@pl")
                .phoneNumber("121212112")
                .vehicles(Collections.emptySet())
                .build());
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