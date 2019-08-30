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
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
