package com.konrad.garagev3.service;

import com.konrad.garagev3.exeption.DuplicateEntryException;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class UserService {

    private UserRepository<? super User> userRepository;
    private RoleRepository roleRepository;
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private String login;


    @Autowired
    public UserService(@Qualifier("userRepository") UserRepository userRepository,
                       @Qualifier("roleRepository") RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        userMapper = Mappers.getMapper(UserMapper.class);
    }

    public UserDto findUserByEmail(String email) throws DuplicateEntryException {
        return userMapper.toUserDto(userRepository.findByEmail(email)
                .orElseThrow(() -> new DuplicateEntryException("user.duplicate.email")));
    }

    public User saveUser(User user) throws DuplicateEntryException {
        Optional<? super User> userFromDatabaseByLogin = userRepository.findByLogin(user.getLogin());
        if (userFromDatabaseByLogin.isPresent()) {
            throw new DuplicateEntryException("user.duplicate.login");
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new DuplicateEntryException("user.duplicate.email");
        }
        if (user.getRoles() != null && !user.getRoles().isEmpty()) {
            user.setRoles(mapStringToRoles(user));
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User updateUser(User user) throws DuplicateEntryException {
        Optional<? extends User> userFromDatabase = userRepository.findById(user.getId());
        if (userFromDatabase.isPresent() && !userFromDatabase.get().getId().equals(user.getId())) {
            throw new DuplicateEntryException("user.duplicate.email");
        }
        if (user.getRoles() != null && !user.getRoles().isEmpty()) {
            user.setRoles(mapStringToRoles(user));
        }
        return userFromDatabase.map(userDB -> {
            userDB.setRoles(user.getRoles());
            userDB.setEmail(user.getEmail());
            userDB.setName(user.getName());
            userDB.setSurname(user.getSurname());
            return userRepository.save(userDB);
        }).orElseThrow(() -> new EntityNotFoundException("user not exist"));
    }

    private Set<Role> mapStringToRoles(User user) {
        return user.getRoles().stream()
                .map(role -> roleRepository.findByName(role.getName()))
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    public UserDto saveUserWithPrivileges(UserDto userDto) {
        UserMapper userMapper = Mappers.getMapper(UserMapper.class);
        User user = userMapper.toUser(userDto);
        return userMapper.toUserDto(userRepository.save(user));
    }

    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findByIdAndDeleted(id, false)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " doesn't exist"));
    }

    public void deleteUser(Long id) {
        userRepository.findById(id).ifPresent(user -> {
            user.setDeleted(true);
            userRepository.save(user);
        });
    }


    public User getInfo() {
        login = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new EntityNotFoundException("User with login + " + login + " doesn't exist"));
    }

    public Page<UserDto> searchUsers(String searchText, List<String> roles, Boolean deleted, PageRequest pageRequest) {
        Page<? extends User> users;
        if (roles != null) {
            List<Role> rolesDB = roleRepository.findByNameIn(roles);
            users = userRepository.findByRolesAndDeleted(roles, searchText, searchText, deleted, pageRequest);
        } else {
            users = userRepository.findByRoleIsNullAndEmailOrNameContainsStringAndDeleted(searchText, deleted, pageRequest);
        }
        return new PageImpl<>(
                users.getContent()
                        .stream()
                        .map(userMapper::toUserDto)
                        .collect(Collectors.toList()), users.getPageable(), users.getTotalElements());
    }


    public void changePassword(String password) {
        Optional<? super User> optionalUser = userRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        if (optionalUser.isPresent()) {
            User user1 = (User) optionalUser.get();
            user1.setPassword(passwordEncoder.encode(password));
            userRepository.save(user1);
        } else
            throw new EntityNotFoundException("User not found");
    }

    public void restoreUser(Long id) {
        userRepository.findByIdAndDeleted(id, true).ifPresent(user -> {
            user.setDeleted(false);
            userRepository.save(user);
        });
    }
}

