package com.alituran.service;

import com.alituran.dto.DtoCar;
import com.alituran.mapper.CarMapper;
import com.alituran.model.Car;
import com.alituran.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {


    private final CarRepository carRepository;

    private final CarMapper carMapper;


    public List<DtoCar> getCarsByBrandName(String brandName) {
        List<Car> carsByBrandName = carRepository.getCarsByBrandName(brandName);
        List<DtoCar> dtoCars = carMapper.toDtoCars(carsByBrandName);
        return dtoCars;
    }
    public List<DtoCar> getCarsByModelYearGreaterThan(int modelYear) {
        return carMapper.toDtoCars(carRepository.getCarsByModelYearGreaterThan(modelYear));
    }

    public List<DtoCar> getCarsInPricePriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return carMapper.toDtoCars(carRepository.getCarsInPricePriceRange(minPrice, maxPrice));
    }

    public List<String> getDistinctFuelTypes(){
        return carRepository.getDistinctFuelTypes();
    }

    public Page<Car> searchCars(String model, String brand, String fuelType, Pageable pageable){
        return carRepository.searchCars(model, brand, fuelType,pageable);
    }


}
