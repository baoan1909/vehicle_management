package com.example.vehicle_management.servicesImpl;

import com.example.vehicle_management.models.ParkingFeeOfVisitor;
import com.example.vehicle_management.repositories.IParkingFeeOfVisitorRepository;
import com.example.vehicle_management.services.IParkingFeeOfVisitorService;

import java.util.List;

public class ParkingFeeOfVisitorServiceImpl implements IParkingFeeOfVisitorService {
    private final IParkingFeeOfVisitorRepository parkingFeeOfVisitorRepository;
    public ParkingFeeOfVisitorServiceImpl(IParkingFeeOfVisitorRepository repository) {
        this.parkingFeeOfVisitorRepository = repository;
    }

    @Override
    public boolean insertParkingFeeOfVisitor(ParkingFeeOfVisitor parkingFeeOfVisitor) {

        return parkingFeeOfVisitorRepository.insert(parkingFeeOfVisitor);
    }

    @Override
    public boolean updateParkingFeeOfVisitor(ParkingFeeOfVisitor parkingFeeOfVisitor) {
        return parkingFeeOfVisitorRepository.update(parkingFeeOfVisitor);
    }

    @Override
    public boolean deleteParkingFeeOfVisitor(int id) {
        return parkingFeeOfVisitorRepository.delete(id);
    }

    @Override
    public ParkingFeeOfVisitor getParkingFeeOfVisitorById(int id) {
        return parkingFeeOfVisitorRepository.getById(id);
    }

    @Override
    public List<ParkingFeeOfVisitor> getAllParkingFeeOfVisitors() {
        return parkingFeeOfVisitorRepository.getAll();
    }
}
