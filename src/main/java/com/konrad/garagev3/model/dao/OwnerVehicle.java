package com.konrad.garagev3.model.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerVehicle implements Serializable {
    private Owner owner;
    private List<Vehicle> vehicles;
}
