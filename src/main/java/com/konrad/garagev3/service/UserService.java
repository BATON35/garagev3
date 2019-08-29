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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service("userService")
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    //    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserDtoMapper userMapper;

    @Autowired
    public UserService(@Qualifier("userRepository") UserRepository userRepository,
                       @Qualifier("roleRepository") RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        userMapper = Mappers.getMapper(UserDtoMapper.class);
    }

    public UserDto findUserByEmail(String email) {
        return userMapper.userToUserDto(userRepository.findByEmail(email));
    }

    public User saveUser(User user) {
        //   user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByName("ROLE_ADMIN");
        user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
        return userRepository.save(user);
    }

    public UserDto saveUserWithPrivileges(UserDto userDto) {
        UserDtoMapper userMapper = Mappers.getMapper(UserDtoMapper.class);
        User user = userMapper.userDtoToUser(userDto);
        //  user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        // user.setRoles(new LinkedHashSet<>(user.getRoles()));
        return userMapper.userToUserDto(userRepository.save(user));
    }

    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    public Role findRoleById(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Role with id " + id + " doesn't exist"));
    }

    @Transactional
    public void deleteUser(String email) {
        userRepository.deleteByEmail(email);
    }

    public UserDto deactivateUser(String email) {
        User user = userRepository.findByEmail(email);
        user.setActive(0);
        return userMapper.userToUserDto(userRepository.save(user));
    }

    public UserDto activateUser(String email) {
        User user = userRepository.findByEmail(email);
        user.setActive(1);
        return userMapper.userToUserDto(userRepository.save(user));
    }

    public List<UserDto> findAllActiveUsers() {
        List<User> users = userRepository.findByActiveIs(1);
        users.sort(Comparator.comparing(User::getEmail));
        return users
                .stream()
                .map(userMapper::userToUserDto)
                .collect(Collectors.toList());
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id" + id + " doesn't exist"));
    }

    public Page<User> findAll(@PageableDefault Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        Page<User> pageUsers = new PageImpl<>(users.getContent(), users.getPageable(), users.getContent().size());
        return pageUsers;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
