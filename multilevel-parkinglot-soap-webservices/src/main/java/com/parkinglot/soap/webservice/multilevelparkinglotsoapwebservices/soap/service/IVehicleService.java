package com.parkinglot.soap.webservice.multilevelparkinglotsoapwebservices.soap.service;

import java.util.List;
import com.parkinglot.soap.webservice.multilevelparkinglotsoapwebservices.soap.bean.Vehicle;

public interface IVehicleService {
    List<Vehicle> getAllVehicles();
    Vehicle getVehicleById(long vehicleId);
    Vehicle getVehicleByLicenseNumber(String licenseNumber);
    boolean addVehicle(Vehicle vehicle);
    void updateVehicle(Vehicle vehicle);
    void deleteVehicle(Vehicle vehicle);
}
