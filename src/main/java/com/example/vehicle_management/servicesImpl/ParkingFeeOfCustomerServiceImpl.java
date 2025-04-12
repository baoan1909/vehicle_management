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
        return parkingFeeOfCustomerRepository.insert(parkingFeeOfCustomer);
    }

    @Override
    public boolean updateParkingFeeOfCustomer(ParkingFeeOfCustomer parkingFeeOfCustomer) {
        return parkingFeeOfCustomerRepository.update(parkingFeeOfCustomer);
    }

    @Override
    public boolean deleteParkingFeeOfCustomer(int id) {
        return parkingFeeOfCustomerRepository.delete(id);
    }

    @Override
    public ParkingFeeOfCustomer getParkingFeeOfCustomerById(int id) {
        return parkingFeeOfCustomerRepository.getById(id);
    }

    @Override
    public List<ParkingFeeOfCustomer> getAllParkingFeeOfCustomers() {
        return parkingFeeOfCustomerRepository.getAll();
    }
}
