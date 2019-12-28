package com.konrad.garagev3.model.dto;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode
public class RoleDto {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
}

