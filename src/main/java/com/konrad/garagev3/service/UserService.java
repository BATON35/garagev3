package com.konrad.garagev3.service;

import com.konrad.garagev3.model.Role;
import com.konrad.garagev3.model.User;
import com.konrad.garagev3.repository.RoleRepository;
import com.konrad.garagev3.repository.UserRepository;
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

    @Autowired
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

    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("ROLE_USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }

    public User SaveUserWithPrivileges(User user) {
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

    @Transactional
    @Modifying
    public void deleteUser(String email) {
        userRepository.deleteUserByEmail(email);
    }

    @Transactional
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