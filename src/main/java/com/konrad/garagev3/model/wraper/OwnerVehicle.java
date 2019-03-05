package com.konrad.garagev3.model.wraper;

import com.konrad.garagev3.model.Owner;
import com.konrad.garagev3.model.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerVehicle implements Serializable {
    private Owner owner;
    private List<Vehicle> vehicles;
}
