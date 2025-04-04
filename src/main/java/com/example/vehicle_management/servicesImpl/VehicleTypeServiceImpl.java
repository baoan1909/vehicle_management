package com.example.vehicle_management.servicesImpl;

import com.example.vehicle_management.models.VehicleType;
import com.example.vehicle_management.repositories.IVehicleTypeRepository;
import com.example.vehicle_management.services.IVehicleTypeService;

import java.util.List;

public class VehicleTypeServiceImpl implements IVehicleTypeService {
    private final IVehicleTypeRepository vehicleTypeRepository;
    public VehicleTypeServiceImpl(IVehicleTypeRepository repository) {
        this.vehicleTypeRepository = repository;
    }

    @Override
    public boolean insertVehicleType(VehicleType vehicleType) {
        return vehicleTypeRepository.insert(vehicleType);
    }

    @Override
    public boolean updateVehicleType(VehicleType vehicleType) {
        return vehicleTypeRepository.update(vehicleType);
    }

    @Override
    public boolean deleteVehicleType(int id) {
        return vehicleTypeRepository.delete(id);
    }

    @Override
    public VehicleType getVehicleTypeById(int id) {
        return vehicleTypeRepository.getById(id);
    }

    @Override
    public List<VehicleType> getAllVehicleTypes() {
        return vehicleTypeRepository.getAll();
    }
}
