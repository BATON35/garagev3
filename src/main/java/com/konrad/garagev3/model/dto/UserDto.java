package com.konrad.garagev3.model.dto;

import com.konrad.garagev3.model.dao.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"password", "id"})
public class UserDto {
    protected Long id;
    protected String login;
    @NotEmpty(message = "*Please provide your password")
    @Length(min = 6, message = "*Your password must have at least 5 characters")
    protected String password;
    @Column(name = "name")
    @NotEmpty(message = "*Please provide your name")
    protected String name;
    protected String surname;
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    protected String email;
    protected String phoneNumber;
    protected Set<Role> roles;
    protected boolean deleted;
    private String createdBy;
    private String lastModifiedBy;
}
