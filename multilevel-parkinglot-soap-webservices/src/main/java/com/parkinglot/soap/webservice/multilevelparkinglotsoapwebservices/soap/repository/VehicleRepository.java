package com.parkinglot.soap.webservice.multilevelparkinglotsoapwebservices.soap.repository;

import org.springframework.data.repository.CrudRepository;
import com.parkinglot.soap.webservice.multilevelparkinglotsoapwebservices.soap.bean.Vehicle;

public interface VehicleRepository extends CrudRepository<Vehicle, Long>  {
    Vehicle findByVehicleId(long vehicleId);
    Vehicle findByVehicleLicenseNumber(String licenseNumber);
}