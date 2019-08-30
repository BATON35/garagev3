package com.konrad.garagev3.service;

//import com.konrad.garagev3.configuration.JwtTokenProvider;

import com.konrad.garagev3.configuration.JwtTokenProvider;
import com.konrad.garagev3.exception.CustomException;
import com.konrad.garagev3.mapper.UserMapper;
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
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserMapper userMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    @Autowired
    public UserService(@Qualifier("userRepository") UserRepository userRepository,
                       @Qualifier("roleRepository") RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        userMapper = Mappers.getMapper(UserMapper.class);
    }

    public UserDto findUserByEmail(String email) {
        return userMapper.toUserDto(userRepository.findByEmail(email));
    }

    public User saveUser(User user) {
        //   user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByName("ROLE_ADMIN");
        user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
        return userRepository.save(user);
    }

    public UserDto saveUserWithPrivileges(UserDto userDto) {
        UserMapper userMapper = Mappers.getMapper(UserMapper.class);
        User user = userMapper.toToUser(userDto);
        //  user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        // user.setRoles(new LinkedHashSet<>(user.getRoles()));
        return userMapper.toUserDto(userRepository.save(user));
    }

    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

//    public Role findRoleById(Long id) {
//        return roleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Role with id " + id + " doesn't exist"));
//    }

    @Transactional
    public void deleteUser(String email) {
        userRepository.deleteByEmail(email);
    }

    public UserDto deactivateUser(String email) {
        User user = userRepository.findByEmail(email);
        user.setActive(0);
        return userMapper.toUserDto(userRepository.save(user));
    }

    public UserDto activateUser(String email) {
        User user = userRepository.findByEmail(email);
        user.setActive(1);
        return userMapper.toUserDto(userRepository.save(user));
    }

    public List<UserDto> findAllActiveUsers() {
        List<User> users = userRepository.findByActiveIs(1);
        users.sort(Comparator.comparing(User::getEmail));
        return users
                .stream()
                .map(userMapper::toUserDto)
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

    public String signin(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return jwtTokenProvider.createToken(username, new ArrayList<>(userRepository.findByName(username).getRoles()));
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
    public String signup(User user) {
        User userExist = userRepository.findByName(user.getName());
        if (userExist == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return jwtTokenProvider.createToken(user.getName(), Collections.singletonList(roleRepository.findByName("ROLE_ADMIN")));
        } else {
            throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
