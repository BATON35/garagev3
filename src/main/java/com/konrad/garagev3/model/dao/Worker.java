package com.konrad.garagev3.model.dao;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode
public class Worker extends User {
    @Enumerated(EnumType.STRING)
    private WorkerType type;
}
