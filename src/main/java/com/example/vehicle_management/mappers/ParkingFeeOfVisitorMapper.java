package com.example.vehicle_management.mappers;

import com.example.vehicle_management.dtos.ParkingFeeOfVisitorDTO;
import com.example.vehicle_management.models.ParkingFeeOfVisitor;
import com.example.vehicle_management.models.VehicleType;
import com.example.vehicle_management.services.IVehicleTypeService;

import java.util.List;
import java.util.stream.Collectors;

public class ParkingFeeOfVisitorMapper {
    public static ParkingFeeOfVisitorDTO toDTO(ParkingFeeOfVisitor parkingFeeOfVisitor, IVehicleTypeService vehicleTypeService) {
        VehicleType vehicleType=vehicleTypeService.getVehicleTypeById(parkingFeeOfVisitor.getVehicleTypeId());
        String vehicleTypeName=(vehicleType!=null) ? vehicleType.getVehicleTypeName() : "Không xác định";
        return new ParkingFeeOfVisitorDTO(
                parkingFeeOfVisitor.getFeeVisitorId(),
                parkingFeeOfVisitor.getPrice(),
                vehicleTypeName,
                parkingFeeOfVisitor.getStartDate()
        );
    }

    public static List<ParkingFeeOfVisitorDTO> toDTOList(List<ParkingFeeOfVisitor> parkingFeeOfVisitorList, IVehicleTypeService vehicleTypeService) {
        return parkingFeeOfVisitorList.stream()
                .map(parkingFeeOfVisitor -> toDTO(parkingFeeOfVisitor, vehicleTypeService))
                .collect(Collectors.toList());
    }
}
