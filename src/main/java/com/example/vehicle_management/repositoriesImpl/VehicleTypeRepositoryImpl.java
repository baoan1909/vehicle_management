package com.example.vehicle_management.repositoriesImpl;

import com.example.vehicle_management.models.VehicleType;
import com.example.vehicle_management.repositories.IVehicleTypeRepository;

import java.util.List;

public class VehicleTypeRepositoryImpl implements IVehicleTypeRepository {
    @Override
    public boolean insert(VehicleType vehicleType) {
        return false;
    }

    @Override
    public boolean update(VehicleType vehicleType) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public VehicleType getById(int id) {
        return null;
    }

    @Override
    public List<VehicleType> getAll() {
        return List.of();
    }
}
