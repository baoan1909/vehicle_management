package com.example.vehicle_management.services;

import com.example.vehicle_management.models.ParkingFeeOfVisitor;

import java.util.List;

public interface IParkingFeeOfVisitorService {
    boolean addParkingFeeOfVisitor(ParkingFeeOfVisitor parkingFeeOfVisitor);
    boolean updateParkingFeeOfVisitor(ParkingFeeOfVisitor parkingFeeOfVisitor);
    boolean deleteParkingFeeOfVisitor(int id);
    ParkingFeeOfVisitor getParkingFeeOfVisitorById(int id);
    List<ParkingFeeOfVisitor> getAllParkingFeeOfVisitors();

}
