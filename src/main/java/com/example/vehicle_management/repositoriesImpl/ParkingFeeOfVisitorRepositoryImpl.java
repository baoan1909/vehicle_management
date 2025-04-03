package com.example.vehicle_management.repositoriesImpl;

import com.example.vehicle_management.models.ParkingFeeOfVisitor;
import com.example.vehicle_management.repositories.IParkingFeeOfVisitorRepository;

import java.util.List;

public class ParkingFeeOfVisitorRepositoryImpl implements IParkingFeeOfVisitorRepository {
    @Override
    public boolean insert(ParkingFeeOfVisitor parkingFeeOfVisitor) {
        return false;
    }

    @Override
    public boolean update(ParkingFeeOfVisitor parkingFeeOfVisitor) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public ParkingFeeOfVisitor getById(int id) {
        return null;
    }

    @Override
    public List<ParkingFeeOfVisitor> getAll() {
        return List.of();
    }
}
