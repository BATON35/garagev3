package com.konrad.garagev3.service;

import com.konrad.garagev3.mapper.UserDtoMapper;
import com.konrad.garagev3.model.dao.Role;
import com.konrad.garagev3.model.dao.User;
import com.konrad.garagev3.model.dto.UserDto;
import com.konrad.garagev3.repository.RoleRepository;
import com.konrad.garagev3.repository.UserRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

@Service("userService")
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    // TODO: 18.03.2019
    public UserService(@Qualifier("userRepository") UserRepository userRepository,
                       @Qualifier("roleRepository") RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User saveUser(UserDto userDto) {
        UserDtoMapper userMapper = Mappers.getMapper(UserDtoMapper.class);
        User user = userMapper.userDtoToUser(userDto);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("ROLE_ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }

    public User SaveUserWithPrivileges(UserDto userDto) {
        UserDtoMapper userMapper = Mappers.getMapper(UserDtoMapper.class);// what does it mean className.class
        User user = userMapper.userDtoToUser(userDto);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        user.setRoles(new HashSet<Role>(user.getRoles()));
        return userRepository.save(user);
    }

    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    public Role findRoleById(int id) {
        return roleRepository.findById(id);
    }

    @Modifying
    public void deleteUser(String email) {
        userRepository.deleteUserByEmail(email);
    }
    
    @Modifying
    public void deleteUserById(int id) {
        userRepository.deleteUserById(id);
    }

    public User deactivateUser(int id) {
        User user = userRepository.findUserById(id);
        user.setActive(0);
        return userRepository.save(user);
    }

    public List<User> findAllUsers() {
        List<User> test = userRepository.findAllActiveUsers();
        test.sort(Comparator.comparing(User::getEmail));
        return test;
    }
}