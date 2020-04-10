package com.konrad.garagev3.auth;

import com.konrad.garagev3.model.dao.User;
import com.konrad.garagev3.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository<User> userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
            return userRepository.findByLogin(login)
                    .map(user -> new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), user.getRoles()
                            .stream()
                            .map(role -> new SimpleGrantedAuthority(role.getName()))
                            .collect(Collectors.toList())))
                    .orElseThrow(() -> new UsernameNotFoundException("user with login " + login + " doesn't exist"));

    }

}
