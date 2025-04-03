package com.example.vehicle_management.repositoriesImpl;

import com.example.vehicle_management.models.ParkingFeeOfCustomer;
import com.example.vehicle_management.repositories.IParkingFeeOfCustomerRepository;

import java.util.List;

public class ParkingFeeOfCustomerRepositoryImpl implements IParkingFeeOfCustomerRepository {
    @Override
    public boolean insert(ParkingFeeOfCustomer parkingFeeOfCustomer) {
        return false;
    }

    @Override
    public boolean update(ParkingFeeOfCustomer parkingFeeOfCustomer) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public ParkingFeeOfCustomer getById(int id) {
        return null;
    }

    @Override
    public List<ParkingFeeOfCustomer> getAll() {
        return List.of();
    }
}
