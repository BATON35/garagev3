package com.konrad.garagev3.controller;

import com.konrad.garagev3.exeption.DuplicateEntryException;
import com.konrad.garagev3.mapper.UserMapper;
import com.konrad.garagev3.model.dao.User;
import com.konrad.garagev3.model.dto.UserDto;
import com.konrad.garagev3.service.UserService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserControllerRest {

    private final UserService userService;
    private final UserMapper userMapper;


    @Autowired
    public UserControllerRest(UserService userService) {
        this.userService = userService;
        userMapper = Mappers.getMapper(UserMapper.class);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/search")
    public Page<UserDto> searchUsers(@RequestParam String searchText, @RequestParam(required = false) List<String> roles, @RequestParam Integer page, @RequestParam Integer size) {
        return userService.searchUsers(searchText, roles, PageRequest.of(page, size));
    }

    @GetMapping("/info")
    public UserDto userInfo() {
        return userMapper.toUserDto(userService.getInfo());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userMapper.toUserDto(userService.findById(id));
    }


    @PostMapping
    public UserDto saveUser(@RequestBody UserDto userDto) throws DuplicateEntryException {
        User user = userMapper.toUser(userDto);
        return userMapper.toUserDto(userService.saveUser(user));
    }

    @PostMapping("/change-password")
    @PreAuthorize("isAuthenticated()")
    public void changePassword(@RequestBody UserDto userDto) {
        userService.changePassword(userDto.getPassword());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public UserDto updateUser(@RequestBody UserDto userDto) throws DuplicateEntryException {
        User user = userMapper.toUser(userDto);
        return userMapper.toUserDto(userService.updateUser(user));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

//    @ExceptionHandler(DataIntegrityViolationException.class)
//    @ResponseStatus(HttpStatus.CONFLICT)
//    @ResponseBody
//    public String duplicateExceptionHandler(DataIntegrityViolationException sqlException) {
//        return sqlException.getMessage();
//    }
//
    @ExceptionHandler(DuplicateEntryException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody()
    public String duplicateEntryExceptionHandler(DuplicateEntryException sqlException) {
        return sqlException.getMessage();
    }

}
