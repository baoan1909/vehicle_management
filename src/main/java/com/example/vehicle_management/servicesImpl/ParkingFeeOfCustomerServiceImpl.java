package com.example.vehicle_management.servicesImpl;

import com.example.vehicle_management.models.ParkingFeeOfCustomer;
import com.example.vehicle_management.services.IParkingFeeOfCustomerService;

import java.util.List;

public class ParkingFeeOfCustomerServiceImpl implements IParkingFeeOfCustomerService {
    @Override
    public boolean addParkingFeeOfCustomer(ParkingFeeOfCustomer parkingFeeOfCustomer) {
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
