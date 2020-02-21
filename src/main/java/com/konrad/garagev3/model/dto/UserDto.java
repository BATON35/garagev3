package com.konrad.garagev3.model.dto;

import com.konrad.garagev3.model.dao.Role;
import com.konrad.garagev3.model.dao.WorkerType;
import lombok.*;
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
@EqualsAndHashCode(exclude = {"password", "active ", "id"})
//@Builder
public class UserDto {
    // private WorkerType workerType;
// private Integer age;
    // @Setter(AccessLevel.NONE)
    // private int id;
    protected String login;
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    protected String email;
    protected Long id;
    protected String phoneNumber;
    @Length(min = 55, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    protected String password;
    @Column(name = "name")
    @NotEmpty(message = "*Please provide your name")
    protected String name;
    protected String surname;
    protected int active;
    protected Set<Role> roles;
    //  private Workshop workshop;
}
