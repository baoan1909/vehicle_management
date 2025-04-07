package com.example.vehicle_management.mappers;


import com.example.vehicle_management.dtos.LostCardDTO;
import com.example.vehicle_management.models.Card;
import com.example.vehicle_management.models.Customer;
import com.example.vehicle_management.models.LostCard;
import com.example.vehicle_management.models.VehicleType;
import com.example.vehicle_management.services.ICardService;
import com.example.vehicle_management.services.ICustomerService;
import com.example.vehicle_management.services.IVehicleTypeService;

import java.util.List;
import java.util.stream.Collectors;

public class LostCardMapper {
    public static LostCardDTO toDTO(LostCard lostCard, ICardService cardService, ICustomerService customerService, IVehicleTypeService vehicleTypeService) {
        Card card = cardService.getCardById(lostCard.getCardId());
        Customer customer = customerService.getCustomerById(lostCard.getCustomerId());
        VehicleType vehicleType = (card != null) ? vehicleTypeService.getVehicleTypeById(card.getVehicleTypeId()) : null;

        String customerName = (customer != null) ? customer.getFullName() : lostCard.getVisitorName();
        String phoneNumber = (customer != null) ? customer.getPhoneNumber() : lostCard.getVisitorPhoneNum();
        String type = (card != null) ? card.getType() : "Không xác định";
        String vehicleTypeName = (vehicleType != null) ? vehicleType.getVehicleTypeName() : "Không xác định";

        return new LostCardDTO(
                lostCard.getLostCardId(),
                lostCard.getNotificationTime(),
                customerName,
                phoneNumber,
                type,
                vehicleTypeName
        );
    }

    public static List<LostCardDTO> toDTOList(List<LostCard> lostCards, ICardService cardService, ICustomerService customerService, IVehicleTypeService vehicleTypeService) {
        return lostCards.stream()
                .map(lostCard -> toDTO(lostCard, cardService, customerService, vehicleTypeService))
                .collect(Collectors.toList());
    }
}
