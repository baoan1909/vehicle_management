package com.example.vehicle_management.servicesImpl;

import com.example.vehicle_management.models.ParkingFeeOfVisitor;
import com.example.vehicle_management.repositories.IParkingFeeOfVisitorRepository;
import com.example.vehicle_management.services.IParkingFeeOfVisitorService;

import java.util.List;

public class ParkingFeeOfVisitorServiceImpl implements IParkingFeeOfVisitorService {
    private final IParkingFeeOfVisitorRepository iParkingFeeOfVisitorRepository;
    public ParkingFeeOfVisitorServiceImpl(IParkingFeeOfVisitorRepository repository) {
        this.iParkingFeeOfVisitorRepository = repository;
    }

    @Override
    public boolean addParkingFeeOfVisitor(ParkingFeeOfVisitor parkingFeeOfVisitor) {
        return false;
    }

    @Override
    public boolean updateParkingFeeOfVisitor(ParkingFeeOfVisitor parkingFeeOfVisitor) {
        return false;
    }

    @Override
    public boolean deleteParkingFeeOfVisitor(int id) {
        return false;
    }

    @Override
    public ParkingFeeOfVisitor getParkingFeeOfVisitorById(int id) {
        return null;
    }

    @Override
    public List<ParkingFeeOfVisitor> getAllParkingFeeOfVisitors() {
        return List.of();
    }
}
