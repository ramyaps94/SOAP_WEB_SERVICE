package com.parkinglot.soap.webservice.multilevelparkinglotsoapwebservices.soap;

import com.parkinglot.soap.webservice.multilevelparkinglotsoapwebservices.soap.bean.Vehicle;
import com.parkinglot.soap.webservice.multilevelparkinglotsoapwebservices.soap.service.impl.VehicleService;
import com.parkinglot.vehicles.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.List;

@Endpoint
public class VehicleDetailsEndPoint {

    @Autowired
    VehicleService service;

    @PayloadRoot(namespace = "http://parkinglot.com/vehicles", localPart = "GetVehicleDetailsByIdRequest")
    @ResponsePayload
    public GetVehicleDetailsByIdResponse processVehicleDetailsRequestById (@RequestPayload GetVehicleDetailsByIdRequest request) {
        //Vehicle vehicle = service.getVehicleById(request.getId());
        Vehicle vehicle = service.getVehicleById(request.getVehicleId());

        return mapVehicleDetailsById(vehicle);
    }

    private GetVehicleDetailsByIdResponse mapVehicleDetailsById(Vehicle vehicle) {
        GetVehicleDetailsByIdResponse response = new GetVehicleDetailsByIdResponse();
        response.setVehicleDetails(mapVehicle(vehicle));
        return response;
    }

    private VehicleDetails mapVehicle(Vehicle vehicle) {
        VehicleDetails vehicleDetails = new VehicleDetails();

        //vehicleDetails.setId(vehicle.getId());
        vehicleDetails.setVehicleId(vehicle.getId());
        vehicleDetails.setVehicleLicenseNumber(vehicle.getVehicleLicenseNumber());
        vehicleDetails.setName(vehicle.getName());
        vehicleDetails.setColor(vehicle.getColor());
        vehicleDetails.setVehicleType(vehicle.getVehicleType());

        return vehicleDetails;
    }

    @PayloadRoot(namespace = "http://parkinglot.com/vehicles", localPart = "GetVehicleDetailsByLicenseRequest")
    @ResponsePayload
    public GetVehicleDetailsByLicenseResponse processVehicleDetailsRequestByLicenseNumber (@RequestPayload GetVehicleDetailsByLicenseRequest request) {

        Vehicle vehicle = service.getVehicleByLicenseNumber(request.getLicense());

        return mapVehicleDetailsByLicense(vehicle);
    }
    private GetVehicleDetailsByLicenseResponse mapVehicleDetailsByLicense(Vehicle vehicle) {
        GetVehicleDetailsByLicenseResponse response = new GetVehicleDetailsByLicenseResponse();
        response.setVehicleDetailsByLicense(mapVehicleByLicense(vehicle));
        return response;
    }


    private VehicleDetailsByLicense mapVehicleByLicense(Vehicle vehicle) {
        VehicleDetailsByLicense vehicleDetails = new VehicleDetailsByLicense();

        //vehicleDetails.setId(vehicle.getId());
        vehicleDetails.setVehicleId(vehicle.getId());
        vehicleDetails.setVehicleLicenseNumber(vehicle.getVehicleLicenseNumber());
        vehicleDetails.setName(vehicle.getName());
        vehicleDetails.setColor(vehicle.getColor());
        vehicleDetails.setVehicleType(vehicle.getVehicleType());

        return vehicleDetails;
    }


    @PayloadRoot(namespace = "http://parkinglot.com/vehicles", localPart = "getAllVehiclesRequest")
    @ResponsePayload
    public GetAllVehiclesResponse getAllVehicles() {
        GetAllVehiclesResponse response = new GetAllVehiclesResponse();
        List<VehicleDetails> vehicleInfoList = new ArrayList<>();
        List<Vehicle> vehicleList = service.getAllVehicles();
        for (int i = 0; i < vehicleList.size(); i++) {
            VehicleDetails ob = new VehicleDetails();
            BeanUtils.copyProperties(vehicleList.get(i), ob);
            vehicleInfoList.add(ob);
        }

        response.getVehicleDetails().addAll(vehicleInfoList);
        return response;
    }

    @PayloadRoot(namespace = "http://parkinglot.com/vehicles", localPart = "addVehicleRequest")
    @ResponsePayload
    public AddVehicleResponse addVehicle(@RequestPayload AddVehicleRequest request) {
        AddVehicleResponse response = new AddVehicleResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        Vehicle vehicle = new Vehicle();
        //vehicle.setId(request.getId());
        vehicle.setId(request.getVehicleId());
        vehicle.setVehicleLicenseNumber(request.getVehicleLicenseNumber());
        vehicle.setName(request.getName());
        vehicle.setColor(request.getColor());
        vehicle.setVehicleType(request.getVehicleType());
        boolean flag = service.addVehicle(vehicle);
        if (flag == false) {
            serviceStatus.setStatusCode("CONFLICT");
            serviceStatus.setMessage("Content Already Available");
            response.setServiceStatus(serviceStatus);
        } else {
            VehicleDetails vehicleDetails = new VehicleDetails();
            BeanUtils.copyProperties(vehicle, vehicleDetails);
            response.setVehicleDetails(vehicleDetails);
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Content Added Successfully");
            response.setServiceStatus(serviceStatus);
        }
        return response;
    }

    @PayloadRoot(namespace = "http://parkinglot.com/vehicles", localPart = "updateVehicleRequest")
    @ResponsePayload
    public UpdateVehicleResponse updateVehicle(@RequestPayload UpdateVehicleRequest request) {
        Vehicle vehicle = new Vehicle();
        BeanUtils.copyProperties(request.getVehicleDetails(), vehicle);
        service.updateVehicle(vehicle);
        ServiceStatus serviceStatus = new ServiceStatus();
        serviceStatus.setStatusCode("SUCCESS");
        serviceStatus.setMessage("Content Updated Successfully");
        UpdateVehicleResponse response = new UpdateVehicleResponse();
        response.setServiceStatus(serviceStatus);
        return response;
    }
    @PayloadRoot(namespace = "http://parkinglot.com/vehicles", localPart = "deleteVehicleRequest")
    @ResponsePayload
    public DeleteVehicleResponse deleteVehicle(@RequestPayload DeleteVehicleRequest request) {
        //Vehicle vehicle = service.getVehicleByLicenseNumber(request.getLicenseNumber());
        Vehicle vehicle = service.getVehicleByLicenseNumber(request.getVehicleLicenseNumber());
        ServiceStatus serviceStatus = new ServiceStatus();
        if (vehicle == null ) {
            serviceStatus.setStatusCode("FAIL");
            serviceStatus.setMessage("Content Not Available");
        } else {
            service.deleteVehicle(vehicle);
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Content Deleted Successfully");
        }
        DeleteVehicleResponse response = new DeleteVehicleResponse();
        response.setServiceStatus(serviceStatus);
        return response;
    }
}
