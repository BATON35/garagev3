package com.konrad.garagev3.model.dao;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Worker extends User {
    @Enumerated(EnumType.STRING)
    private WorkerType type;
}
