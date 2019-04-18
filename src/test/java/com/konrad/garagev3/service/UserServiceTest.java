package com.konrad.garagev3.service;

import com.konrad.garagev3.model.dao.User;
import com.konrad.garagev3.repository.RoleRepository;
import com.konrad.garagev3.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static com.konrad.garagev3.service.UserServiceTestData.TEST_USER;
import static com.konrad.garagev3.service.UserServiceTestData.TEST_USER_DTO;
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
    // TODO: 15.04.2019 Autowired?
    private UserService userServiceUnderTest;
    private User user;

    @Before
    public void setUp() {
        initMocks(this);
        userServiceUnderTest = new UserService(mockUserRepository,
                mockRoleRepository,
                mockBCryptPasswordEncoder);
        user = TEST_USER.builder().build();

        Mockito.when(mockUserRepository.save(any())).thenReturn(user);
        Mockito.when(mockUserRepository.findByEmail(anyString())).thenReturn(user);
    }

    @Test
    public void findUserByEmail() {
        // Setup
        final String email = TEST_USER.getEmail();

        // Run the test
        final User result = userServiceUnderTest.findUserByEmail(email);

        // Verify the results
        assertEquals(user, result);
    }

    @Test
    public void saveUser() {
        // Setup

        // Run the test
        final User result = userServiceUnderTest.saveUser(TEST_USER_DTO);

        // Verify the results
        assertEquals(TEST_USER, result);
    }

    @Test
    public void saveUserWithPrivileges() {
        // Setup

        // Run the test
        final User result = userServiceUnderTest.saveUserWithPrivileges(TEST_USER_DTO);

        // Verify the results
        assertEquals(TEST_USER, result);
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
    }
}