package com.parkinglot.soap.webservice.multilevelparkinglotsoapwebservices.soap.bean;

import com.parkinglot.vehicles.VehicleType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="VEHICLE")
public class Vehicle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="VEHICLE_ID")
    private Long vehicleId;

    @Column(name = "LICENSE_NUMBER")
    private String vehicleLicenseNumber;

    @Column(name = "COLOR")
    private String color;

    @Column(name="NAME")
    private String name;

    @Column(name="VEHICLE_TYPE")
    private com.parkinglot.vehicles.VehicleType vehicleType;


    public Long getId() {
        return vehicleId;
    }

    public void setId(Long id) {
        this.vehicleId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVehicleLicenseNumber(){
        return vehicleLicenseNumber;
    }

    public void setVehicleLicenseNumber(String vehicleLicenseNumber){
        this.vehicleLicenseNumber = vehicleLicenseNumber;
    }

    public VehicleType getVehicleType(){
        return vehicleType;
    }


    public void setVehicleType(com.parkinglot.vehicles.VehicleType vehicleType){
        this.vehicleType = vehicleType;
    }

    public String getColor(){
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
