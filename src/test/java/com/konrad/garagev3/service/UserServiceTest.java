package com.konrad.garagev3.service;

import com.konrad.garagev3.repository.RoleRepository;
import com.konrad.garagev3.repository.UserRepository;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private RoleRepository mockRoleRepository;
//    @Mock
//    private BCryptPasswordEncoder mockBCryptPasswordEncoder;

    private UserService userServiceUnderTest;

//    @Before
//    public void setUp() {
//        // initMocks(this);
//        userServiceUnderTest = new UserService(mockUserRepository,
//                mockRoleRepository,
//                mockBCryptPasswordEncoder);
//        Mockito.when(mockUserRepository.save(any(User.class))).thenReturn(TEST_USER);
////        Mockito.when(mockUserRepository.findByActiveIs(1)).thenReturn(Arrays.asList(TEST_USER, TEST_USER_1));
////        Mockito.when(mockUserRepository.findByEmail(TEST_USER.getEmail())).thenReturn(TEST_USER);
////         Mockito.when(mockRoleRepository.findAll()).thenReturn(new ArrayList<>(ALL_ROLES));
//    }
//
//
//    @Test
//    public void SaveVehicle() {
//        // Setup
//
//        // Run the test
//        User result = userServiceUnderTest.SaveVehicle(TEST_USER);
//
//        // Verify the results
//        assertEquals(TEST_USER, result);
//    }
//
//    @Ignore
//    @Test
//    public void findUserByEmail() {
//        // Setup
//        final String email = TEST_USER.getEmail();
//
//        // Run the test
//        final UserDto result = userServiceUnderTest.findUserByEmail(email);
//
//        // Verify the results
//        assertEquals(TEST_USER_DTO, result);
//    }
//
//    @Ignore
//    @Test
//    public void saveUserWithPrivileges() {
//        // Setup
//
//        // Run the test
//        final UserDto result = userServiceUnderTest.saveUserWithPrivileges(TEST_USER_DTO);
//
//        // Verify the results
//        assertEquals(TEST_USER_DTO, result);
//    }
//
//    // TODO: 10.07.2019 should we test this method(this methods don't implement new logic)
//    @Test
//    public void findRoleById() {
//    }
//
//    @Test
//    public void deleteUser() {
//    }
//
//    @Test
//    public void deleteUserById() {
//    }
//
//    @Test
//    public void deactivateUser() {
//        Mockito.when(mockUserRepository
//                .findByEmail(TEST_USER_ACTIVE.getEmail()))
//                .thenReturn(TEST_USER_ACTIVE);
//
//        userServiceUnderTest.deactivateUser(TEST_USER_ACTIVE.getEmail());
//// TODO: 10.07.2019 How to check did  setActive from User Class is invoke.
//        Mockito.verify(mockUserRepository).save(any(User.class));
//    }
//
//    @Test
//    public void activateUser() {
//        // TODO: 10.07.2019 misleading test
////        Mockito.when(mockUserRepository
////                .findByEmail(TEST_USER_INACTIVE.getEmail()))
////                .thenReturn(TEST_USER_INACTIVE);
////
////        final UserDto result = userServiceUnderTest.activateUser(TEST_USER_INACTIVE.getEmail());
////
////        Assert.assertEquals(1, result.getActive());
//    }
//
//    @Ignore
//    @Test
//    public void findAllActiveUsers() {
//
//        List users = userServiceUnderTest.findAllActiveUsers();
//
//        assertEquals(Arrays.asList(TEST_USER_DTO, TEST_USER_DTO_1), users);
//    }
}