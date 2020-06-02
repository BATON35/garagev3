package com.konrad.garagev3.model.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Set;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode
@Inheritance
@EntityListeners(AuditingEntityListener.class)
public class User{
    @Id
    @GeneratedValue
    protected Long id;
    @Column(unique = true)
    protected String login;
    protected String password;
    protected String name;
    protected String surname;
    @Column(unique = true)
    protected String email;
    protected String phoneNumber;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    protected Set<Role> roles;
    protected boolean deleted;
    @CreatedBy
    private String createdBy;
    @LastModifiedBy
    private String lastModifiedBy;
}
