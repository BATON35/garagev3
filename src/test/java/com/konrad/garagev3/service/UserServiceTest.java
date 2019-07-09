package com.konrad.garagev3.service;

import com.konrad.garagev3.model.dao.User;
import com.konrad.garagev3.model.dto.UserDto;
import com.konrad.garagev3.repository.RoleRepository;
import com.konrad.garagev3.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.List;

import static com.konrad.garagev3.service.UserServiceTestData.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.MockitoAnnotations.initMocks;


//@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private RoleRepository mockRoleRepository;
    @Mock
    private BCryptPasswordEncoder mockBCryptPasswordEncoder;

    private UserService userServiceUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        userServiceUnderTest = new UserService(mockUserRepository,
                mockRoleRepository,
                mockBCryptPasswordEncoder);
        Mockito.when(mockUserRepository.save(any(User.class))).thenReturn(TEST_USER);
        Mockito.when(mockUserRepository.findAllActiveUsers()).thenReturn(Arrays.asList(TEST_USER, TEST_USER_1));
        Mockito.when(mockUserRepository.findByEmail(anyString())).thenReturn(TEST_USER);
    }

    @Test
    public void saveUser() {
        // Setup

        // Run the test
        User result = userServiceUnderTest.saveUser(TEST_USER);

        // Verify the results
        assertEquals(TEST_USER, result);
    }


    @Test
    public void findUserByEmail() {
        // Setup
        final String email = TEST_USER.getEmail();

        // Run the test
        final UserDto result = userServiceUnderTest.findUserByEmail(email);

        // Verify the results
        assertEquals(TEST_USER_DTO, result);
    }


    @Test
    public void saveUserWithPrivileges() {
        // Setup

        // Run the test
        final UserDto result = userServiceUnderTest.saveUserWithPrivileges(TEST_USER_DTO);

        // Verify the results
        assertEquals(TEST_USER_DTO, result);
    }

    @Test
    public void findAllRoles() {
    }

    @Test
    public void findRoleById() {
    }

    @Test
    public void deleteUser() {
    }

    @Test
    public void deleteUserById() {
    }

    @Test
    public void deactivateUser() {
    }

    @Test
    public void activateUser() {
    }

    @Test
    public void findAllUsers() {
        List users = userServiceUnderTest.findAllActiveUsers();

        Assert.assertEquals(Arrays.asList(TEST_USER_DTO, TEST_USER_DTO_2), users);
    }
}