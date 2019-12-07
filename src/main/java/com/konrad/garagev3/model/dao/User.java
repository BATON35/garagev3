package com.konrad.garagev3.model.dao;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode
@Inheritance
public class User{
    @Id
    @GeneratedValue
    protected Long id;
    @Column(unique = true)
    protected String email;
    protected String password;
    @Column(unique = true)
    protected String name;
    protected String surname;
    protected Integer active;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    protected Set<Role> roles;
}
