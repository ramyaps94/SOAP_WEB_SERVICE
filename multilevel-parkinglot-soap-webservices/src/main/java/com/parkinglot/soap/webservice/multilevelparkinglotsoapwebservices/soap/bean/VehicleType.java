package com.parkinglot.soap.webservice.multilevelparkinglotsoapwebservices.soap.bean;

public enum VehicleType {
     MOTORBIKE,
     CAR,
     BUS;

     public String value() {
          return name();
     }

     public static VehicleType fromValue(String v) {
          return valueOf(v);
     }
}