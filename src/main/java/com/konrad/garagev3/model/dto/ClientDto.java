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

    // TODO: 15.07.2019  czy dto powinien impelmentowac hashCode and equals
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        ClientDto clientDto = (ClientDto) o;
//        return getActive() == clientDto.getActive() &&
//                Objects.equals(getName(), clientDto.getName()) &&
//                Objects.equals(getSurname(), clientDto.getSurname()) &&
//                Objects.equals(getEmail(), clientDto.getEmail()) &&
//                Objects.equals(getPhoneNumber(), clientDto.getPhoneNumber());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getName(), getActive(), getSurname(), getEmail(), getPhoneNumber());
//    }
}
