package com.konrad.garagev3.model.dao;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@EqualsAndHashCode
public class Role {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
}
