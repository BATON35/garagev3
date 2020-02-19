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
import java.util.stream.Stream;


@Service
public class UserService {

    private UserRepository<? super User> userRepository;
    private RoleRepository roleRepository;
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private String name;


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

    public User saveUser(User user) throws DuplicateEntryException {
        User userFromDatabase = userRepository.findByEmail(user.getEmail());
        if (userFromDatabase != null) {
            throw new DuplicateEntryException("user.duplicate.email");
        }
        user.setActive(1);
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
        userFromDatabase.get().setRoles(user.getRoles());
        userFromDatabase.get().setEmail(user.getEmail());
        userFromDatabase.get().setName(user.getName());
        userFromDatabase.get().setSurname(user.getSurname());
        userFromDatabase.get().setActive(user.getActive());
        return userRepository.save(userFromDatabase.get());
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
        List<? extends User> users = userRepository.findByActiveIs(1);
        users.sort(Comparator.comparing(User::getEmail));
        return users
                .stream()
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " doesn't exist"));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


    public User getInfo() {
        name = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("User with name + " + name + " doesn't exist"));
    }

    public Page<UserDto> searchUsers(String searchText, List<String> roles, PageRequest pageRequest) {
        Page<? extends User> users;
        if (roles != null) {
            List<Role> rolesDB = roleRepository.findByNameIn(roles);
            users = userRepository.findByRoles(roles, searchText, searchText, pageRequest);
            //todo dlaczego nie mogę usuną obiekut z listy w ten sposób
//            List<Role> byNameNotIn = roleRepository.findByNameNotIn(roles);
//            for (int i = 0; i < users.getContent().size(); i++) {
//                if (users.getContent().get(i).getRoles().stream().anyMatch(role -> role.getName().equals(byNameNotIn.get(0).getName()))) {
//                    users.getContent().remove(users.getContent().get(i));
//                }
//            }
        } else {
            users = userRepository.findByRoleIsNullAndEmailOrNameContainsString(searchText, pageRequest);
        }
        return new PageImpl<>(
                users.getContent()
                        .stream()
                        .map(userMapper::toUserDto)
                        .collect(Collectors.toList()), users.getPageable(), users.getTotalElements());
    }


}

