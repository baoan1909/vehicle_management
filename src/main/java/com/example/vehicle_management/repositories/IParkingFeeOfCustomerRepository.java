package com.example.vehicle_management.repositories;


import com.example.vehicle_management.models.ParkingFeeOfCustomer;
import com.example.vehicle_management.models.VehicleType;

public interface IParkingFeeOfCustomerRepository extends IRepository<ParkingFeeOfCustomer> {
    ParkingFeeOfCustomer  findByTicketTypeAndVehicleType(int ticketTypeId, int vehicleTypeId);
}
