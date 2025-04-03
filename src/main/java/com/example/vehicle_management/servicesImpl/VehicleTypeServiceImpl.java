package com.example.vehicle_management.servicesImpl;

import com.example.vehicle_management.models.VehicleType;
import com.example.vehicle_management.repositories.IVehicleTypeRepository;
import com.example.vehicle_management.services.IVehicleTypeService;

import java.util.List;

public class VehicleTypeServiceImpl implements IVehicleTypeService {
    private final IVehicleTypeRepository iVehicleTypeRepository;
    public VehicleTypeServiceImpl(IVehicleTypeRepository repository) {
        this.iVehicleTypeRepository = repository;
    }

    @Override
    public boolean insertVehicleType(VehicleType vehicleType) {
        return false;
    }

    @Override
    public boolean updateVehicleType(VehicleType vehicleType) {
        return false;
    }

    @Override
    public boolean deleteVehicleType(int id) {
        return false;
    }

    @Override
    public VehicleType getVehicleTypeById(int id) {
        return null;
    }

    @Override
    public List<VehicleType> getAllVehicleTypes() {
        return List.of();
    }
}
