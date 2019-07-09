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
import java.util.*;

@Service("userService")
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserDtoMapper userMapper;

    @Autowired
    public UserService(@Qualifier("userRepository") UserRepository userRepository,
                       @Qualifier("roleRepository") RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        userMapper = Mappers.getMapper(UserDtoMapper.class);
    }

    public UserDto findUserByEmail(String email) {
        return userMapper.userToUserDto(userRepository.findByEmail(email));
    }

    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("ROLE_ADMIN");
        user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
        return userRepository.save(user);
    }

    public UserDto saveUserWithPrivileges(UserDto userDto) {
        UserDtoMapper userMapper = Mappers.getMapper(UserDtoMapper.class);
        User user = userMapper.userDtoToUser(userDto);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        user.setRoles(new LinkedHashSet<>(user.getRoles()));
        return userMapper.userToUserDto(userRepository.save(user));
    }

    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    Role findRoleById(int id) {
        return roleRepository.findById(id);
    }

    @Transactional
    public void deleteUser(String email) {
        userRepository.deleteUserByEmail(email);
    }

    public UserDto deactivateUser(String email) {
        User user = userRepository.findByEmail(email);
        user.setActive(0);
        return userMapper.userToUserDto(userRepository.save(user));
    }

    UserDto activateUser(String email) {
        User user = userRepository.findByEmail(email);
        user.setActive(1);
        return userMapper.userToUserDto(userRepository.save(user));
    }

    public List<UserDto> findAllActiveUsers() {
        List<User> users = userRepository.findAllActiveUsers();
        users.sort(Comparator.comparing(User::getEmail));
        List<UserDto> usersDto = new ArrayList<>();
        for (User user: users) {
          usersDto.add(userMapper.userToUserDto(user));
        }
        return usersDto;
    }
}