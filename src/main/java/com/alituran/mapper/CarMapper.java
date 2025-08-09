package com.alituran.mapper;

import com.alituran.dto.DtoCar;
import com.alituran.model.Car;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {

    DtoCar toDtoCar(Car car);

    List<DtoCar> toDtoCars(List<Car> cars);

    Car toCar(DtoCar dtoCar);
}
