package com.konrad.garagev3.model.dto;

import com.konrad.garagev3.model.dao.Vehicle;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ClientDto {
    @Length(min = 3, max = 127, message = "Pole imie musi musi składać sie z co najmniej trzech liter")
    @NotEmpty(message = "Wypełnij pole imie klienta")
    private String name;
    private int active;
    @Length(min = 3, max = 127, message = "Pole nazwisko musi musi składać sie z co najmniej trzech liter")
    @NotEmpty(message = "Wypełnij pole nazwisko klienta")
    private String surname;
    @Email(message = "Podaj poprawny adres email")
    private String email;
    private String phoneNumber;
    private List<VehicleDto> vehicles;
}
