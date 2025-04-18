package com.example.vehicle_management.services;

import com.example.vehicle_management.models.ParkingFeeOfCustomer;

import java.util.List;

public interface IParkingFeeOfCustomerService {
    boolean insertParkingFeeOfCustomer(ParkingFeeOfCustomer parkingFeeOfCustomer);
    boolean updateParkingFeeOfCustomer(ParkingFeeOfCustomer parkingFeeOfCustomer);
    boolean deleteParkingFeeOfCustomer(int id);
    ParkingFeeOfCustomer getParkingFeeOfCustomerById(int id);
    List<ParkingFeeOfCustomer> getAllParkingFeeOfCustomers();
    ParkingFeeOfCustomer findByTicketTypeAndVehicleType (int ticketTypeId, int vehicleTypeId);
}
