package com.alituran.service;

import com.alituran.dto.DtoCar;
import com.alituran.mapper.CarMapper;
import com.alituran.model.Car;
import com.alituran.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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


    @Cacheable(value = "getcarsbybrandname",key = "#brandName",unless = "#result==null || #result.isEmpty()")
    public List<DtoCar> getCarsByBrandName(String brandName) {
        List<Car> carsByBrandName = carRepository.getCarsByBrandName(brandName);
        List<DtoCar> dtoCars = carMapper.toDtoCars(carsByBrandName);
        return dtoCars;
    }

    @Cacheable(value = "getcarsbymodelyeargreaterthan",key = "#modelYear",unless = "#result==null || #result.isEmpty()")
    public List<DtoCar> getCarsByModelYearGreaterThan(int modelYear) {
        return carMapper.toDtoCars(carRepository.getCarsByModelYearGreaterThan(modelYear));
    }

    @Cacheable(value = "getcarsinpricerange",key = "#minPrice + '-' +  #maxPrice",unless = "#result==null || #result.isEmpty()")
    public List<DtoCar> getCarsInPricePriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return carMapper.toDtoCars(carRepository.getCarsInPricePriceRange(minPrice, maxPrice));
    }
    @Cacheable(value = "getdistinctfueltypes",key = "'all'",unless = "#result==null || #result.isEmpty()")
    public List<String> getDistinctFuelTypes(){
        return carRepository.getDistinctFuelTypes();
    }

    @Cacheable(value = "searchcars",
            key = "#model + '-' + #brand + '-' + #fuelType + '-' + #pageable.pageNumber + '-' + #pageable.pageSize"
            ,unless = "#result==null || #result.isEmpty()")
    public Page<Car> searchCars(String model, String brand, String fuelType, Pageable pageable){
        return carRepository.searchCars(model, brand, fuelType,pageable);
    }

    @CacheEvict(value = {
            "searchcars",
            "getcarsinpricerange",
            "getcarsbybrandname",
            "getcarsbymodelyeargreaterthan",
            "getdistinctfueltypes"
    }, allEntries = true)
    public DtoCar saveCar(DtoCar dtoCar){
        Car car = carMapper.toCar(dtoCar);
        carRepository.save(car);
        return dtoCar;
    }


}
