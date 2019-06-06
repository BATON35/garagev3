package com.konrad.garagev3.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    @Length(min = 3, max = 127, message = "Pole imie musi musi składać sie z co najmniej trzech liter")
    @NotEmpty(message = "Wypełnij pole imie klienta")
    private String name;
    @Length(min = 3, max = 127, message = "Pole nazwisko musi musi składać sie z co najmniej trzech liter")
    @NotEmpty(message = "Wypełnij pole nazwisko klienta")
    private String surname;
    @Email
    private String email;
    private String phoneNumber;
}
