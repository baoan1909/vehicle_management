package com.example.vehicle_management.mappers;

import com.example.vehicle_management.dtos.CardDTO;
import com.example.vehicle_management.models.Card;
import com.example.vehicle_management.models.TicketType;
import com.example.vehicle_management.models.VehicleType;
import com.example.vehicle_management.services.ITicketTypeService;
import com.example.vehicle_management.services.IVehicleTypeService;

import java.util.List;
import java.util.stream.Collectors;

public class CardMapper {
    public static CardDTO toDTO(Card card, IVehicleTypeService vehicleTypeService, ITicketTypeService ticketTypeService) {
        VehicleType vehicleType = vehicleTypeService.getVehicleTypeById(card.getVehicleTypeId());
        String vehicleTypeName = (vehicleType != null) ? vehicleType.getVehicleTypeName() : "Không xác định";
        int vehicleTypeId = card.getVehicleTypeId();

        TicketType ticketType= ticketTypeService.getTicketTypeById(card.getCardId());
        int ticketTypeId = (ticketType != null) ? ticketType.getTicketTypeId() : 0;
        String ticketTypeName = (ticketType != null) ? ticketType.getTicketTypeName() : "";

        // Tạo CardDTO
        return new CardDTO(
                card.getCardId(),
                card.getCardNumber(),
                card.getType(),
                vehicleTypeName,
                card.getIsCreated(),
                card.getIsUsed(),
                vehicleTypeId,
                ticketTypeId,
                ticketTypeName
        );
    }

    public static List<CardDTO> toDTOList(List<Card> cards, IVehicleTypeService vehicleTypeService, ITicketTypeService ticketTypeService) {
        return cards.stream()
                .map(card -> toDTO(card, vehicleTypeService, ticketTypeService))
                .collect(Collectors.toList());
    }
}
