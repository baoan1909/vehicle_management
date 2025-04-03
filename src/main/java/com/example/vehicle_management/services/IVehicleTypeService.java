package com.example.vehicle_management.services;

import com.example.vehicle_management.models.VehicleType;

import java.util.List;

public interface IVehicleTypeService {
    boolean insertVehicleType(VehicleType vehicleType);
    boolean updateVehicleType(VehicleType vehicleType);
    boolean deleteVehicleType(int id);
    VehicleType getVehicleTypeById(int id);
    List<VehicleType> getAllVehicleTypes();
}
