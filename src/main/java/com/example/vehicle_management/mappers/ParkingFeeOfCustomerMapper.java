package com.example.vehicle_management.mappers;

import com.example.vehicle_management.dtos.ParkingFeeOfCustomerDTO;
import com.example.vehicle_management.models.ParkingFeeOfCustomer;
import com.example.vehicle_management.models.TicketType;
import com.example.vehicle_management.models.VehicleType;
import com.example.vehicle_management.services.ITicketTypeService;
import com.example.vehicle_management.services.IVehicleTypeService;

import java.util.List;
import java.util.stream.Collectors;

public class ParkingFeeOfCustomerMapper {
    public static ParkingFeeOfCustomerDTO toDTO(ParkingFeeOfCustomer parkingFeeOfCustomer, ITicketTypeService ticketTypeService, IVehicleTypeService vehicleTypeService) {
        TicketType ticketType=ticketTypeService.getTicketTypeById(parkingFeeOfCustomer.getTicketTypeId());
        String ticketTypeName= (ticketType != null) ? ticketType.getTicketTypeName() : "Không xác định";
        VehicleType vehicleType=vehicleTypeService.getVehicleTypeById(parkingFeeOfCustomer.getVehicleTypeId());
        String vehicleTypeName=(vehicleType!=null) ? vehicleType.getVehicleTypeName() : "Không xác định";


        return new ParkingFeeOfCustomerDTO(
             parkingFeeOfCustomer.getFeeCustomerId(),
             ticketTypeName,
             vehicleTypeName,
             parkingFeeOfCustomer.getPrice(),
             parkingFeeOfCustomer.getStartDate()
        );
    }

    public static List<ParkingFeeOfCustomerDTO> toDTOList(List<ParkingFeeOfCustomer> parkingFeeOfCustomerList, ITicketTypeService ticketTypeService , IVehicleTypeService vehicleTypeService) {
        return parkingFeeOfCustomerList.stream()
                .map(parkingFeeOfCustomer-> toDTO(parkingFeeOfCustomer, ticketTypeService, vehicleTypeService))
                .collect(Collectors.toList());
    }
}
