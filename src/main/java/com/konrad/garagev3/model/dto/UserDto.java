package com.konrad.garagev3.model.dto;

import com.konrad.garagev3.model.dao.Role;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"password", "active ", "id"})
public class UserDto {
   // @Setter(AccessLevel.NONE)
   // private int id;
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    private String email;
    private Long id;
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    private String password;
    @Column(name = "name")
    @NotEmpty(message = "*Please provide your name")
    private String name;
    private int active;
    private Set<Role> roles;
  //  private Workshop workshop;
}
