package com.alituran.controller;

import com.alituran.dto.CarSearchRequest;
import com.alituran.dto.DtoCar;
import com.alituran.dto.RestPageableResponse;
import com.alituran.mapper.CarMapper;
import com.alituran.model.Car;
import com.alituran.service.CarService;
import com.alituran.utils.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarController {


    private final CarService carService;
    private final CarMapper carMapper;

    @GetMapping(path = "/by-brand")
    public ResponseEntity<List<DtoCar>> getCarsByBrandName(@RequestParam("brandName") String brandName){
        return ResponseEntity.ok(carService.getCarsByBrandName(brandName));
    }

    @GetMapping(path = "/by-model-year")
    public ResponseEntity<List<DtoCar>> getCarsByModelYearGreaterThan(@RequestParam("modelYear") int modelYear){
        return ResponseEntity.ok(carService.getCarsByModelYearGreaterThan(modelYear));
    }

    @GetMapping(path = "/by-price-range")
    public ResponseEntity<List<DtoCar>> getCarsInPricePriceRange( @RequestParam("minPrice") BigDecimal minPrice,
                                                                  @RequestParam("maxPrice") BigDecimal maxPrice) {
        return ResponseEntity.ok(carService.getCarsInPricePriceRange(minPrice, maxPrice));
    }

    @GetMapping(path = "/fuel-types")
    public ResponseEntity<List<String>> getDistinctFuelTypes(){
        return ResponseEntity.ok(carService.getDistinctFuelTypes());
    }

    @PostMapping(path = "/search")
    public ResponseEntity<RestPageableResponse> searchCars(@RequestBody CarSearchRequest request){

        Pageable pageable = PageUtil.toPageable(request.getPageable());
        Page<Car> cars = carService.searchCars(request.getModel(), request.getBrand(),
                request.getFuelType(),pageable);
        return ResponseEntity.ok(PageUtil.toRestPageableResponse(cars, carMapper.toDtoCars(cars.getContent())));
    }

    @PostMapping(path = "/savecar")
    public ResponseEntity<DtoCar> saveCar(@RequestBody DtoCar dtoCar){
        return ResponseEntity.ok(carService.saveCar(dtoCar));
    }
}
