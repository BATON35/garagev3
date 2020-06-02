package com.konrad.garagev3.service;

import java.util.List;

public interface CarService {
    List<String> getBrands();
    List<String> getModels(String brand);

    List<String> getProductionDate(String brand, String model);
}
