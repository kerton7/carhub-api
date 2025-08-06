package com.alituran.repository;

import com.alituran.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {


    @Query("SELECT c FROM Car c WHERE LOWER(c.carModel.brand.brandName) =LOWER(:brandName)")
    List<Car> getCarsByBrandName(@Param("brandName") String brandName);


    @Query("SELECT c FROM Car c WHERE c.carModel.modelYear >:modelYear")
    List<Car> getCarsByModelYearGreaterThan(@Param("modelYear") int modelYear);


    @Query("SELECT c FROM Car c WHERE c.price BETWEEN :minPrice and :maxPrice")
    List<Car> getCarsInPricePriceRange(@Param("minPrice") BigDecimal minPrice,
                                       @Param("maxPrice") BigDecimal maxPrice);


    @Query("SELECT DISTINCT c.fuelType FROM Car c")
    List<String> getDistinctFuelTypes();


    @Query("""
       SELECT c FROM Car c WHERE (c.carModel.brand.brandName=:brand OR :brand IS NULL) AND 
       (c.carModel.modelName =:model OR :model IS NULL) AND 
       (c.fuelType = :fuelType OR :fuelType IS NULL )
""")
    Page<Car> searchCars(@Param("brand") String brand,
                         @Param("model") String model,
                         @Param("fuelType") String fuelType,
                         Pageable pageable);




}
