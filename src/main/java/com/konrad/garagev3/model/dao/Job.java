package com.konrad.garagev3.model.dao;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@EqualsAndHashCode
@EntityListeners(AuditingEntityListener.class)
public class Job {
    @Id
    @GeneratedValue
    private Long id;

    @CreatedDate
    private LocalDateTime createdDate;

    @ManyToOne
    private CarService carService;

    @ManyToMany
    @JoinTable
    private List<Part> parts;

    @ManyToOne
    private Worker worker;

    @ManyToOne()
    @JsonManagedReference
    private Vehicle vehicle;
}
