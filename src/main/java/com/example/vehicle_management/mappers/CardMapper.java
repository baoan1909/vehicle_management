package com.example.vehicle_management.mappers;

import com.example.vehicle_management.dtos.CardDTO;
import com.example.vehicle_management.models.Card;
import com.example.vehicle_management.models.VehicleType;
import com.example.vehicle_management.services.IVehicleTypeService;

import java.util.List;
import java.util.stream.Collectors;

public class CardMapper {
    public static CardDTO toDTO(Card card, IVehicleTypeService vehicleTypeService) {
        VehicleType vehicleType = vehicleTypeService.getVehicleTypeById(card.getVehicleTypeId());
        String vehicleTypeName = (vehicleType != null) ? vehicleType.getVehicleTypeName() : "Không xác định";

        // Tạo CardDTO
        return new CardDTO(
                card.getCardId(),
                card.getCardNumber(),
                card.getType(),
                vehicleTypeName,
                card.getIsCreated(),
                card.getIsUsed()
        );
    }

    public static List<CardDTO> toDTOList(List<Card> cards, IVehicleTypeService vehicleTypeService) {
        return cards.stream()
                .map(card -> toDTO(card, vehicleTypeService))
                .collect(Collectors.toList());
    }
}
