package com.konrad.garagev3.model.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = { "active ", "id"})
public class ClientDto implements Serializable {
    @Length(min = 3, max = 127, message = "Pole imie musi musi składać sie z co najmniej trzech liter")
    @NotEmpty(message = "Wypełnij pole imie klienta")
    private String name;
    private Long id;
    private boolean deleted;
    @Length(min = 3, max = 127, message = "Pole nazwisko musi musi składać sie z co najmniej trzech liter")
    @NotEmpty(message = "Wypełnij pole nazwisko klienta")
    private String surname;
    @Email(message = "Podaj poprawny adres email")
    private String email;
    private String phoneNumber;
    private Set<VehicleDto> vehicles;
}
