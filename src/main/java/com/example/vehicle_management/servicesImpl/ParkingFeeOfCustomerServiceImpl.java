package com.example.vehicle_management.servicesImpl;

import com.example.vehicle_management.models.ParkingFeeOfCustomer;
import com.example.vehicle_management.repositories.IParkingFeeOfCustomerRepository;
import com.example.vehicle_management.services.IParkingFeeOfCustomerService;

import java.util.List;

public class ParkingFeeOfCustomerServiceImpl implements IParkingFeeOfCustomerService {
    private final IParkingFeeOfCustomerRepository parkingFeeOfCustomerRepository;
    public ParkingFeeOfCustomerServiceImpl(IParkingFeeOfCustomerRepository repository) {
        this.parkingFeeOfCustomerRepository = repository;
    }

    @Override
    public boolean insertParkingFeeOfCustomer(ParkingFeeOfCustomer parkingFeeOfCustomer) {
        return false;
    }

    @Override
    public boolean updateParkingFeeOfCustomer(ParkingFeeOfCustomer parkingFeeOfCustomer) {
        return false;
    }

    @Override
    public boolean deleteParkingFeeOfCustomer(int id) {
        return false;
    }

    @Override
    public ParkingFeeOfCustomer getParkingFeeOfCustomerById(int id) {
        return null;
    }

    @Override
    public List<ParkingFeeOfCustomer> getAllParkingFeeOfCustomers() {
        return List.of();
    }
}
