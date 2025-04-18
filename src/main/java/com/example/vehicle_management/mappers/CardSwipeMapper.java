package com.example.vehicle_management.mappers;

import com.example.vehicle_management.dtos.CardSwipeDTO;
import com.example.vehicle_management.models.Card;
import com.example.vehicle_management.models.CardSwipe;
import com.example.vehicle_management.models.TicketType;
import com.example.vehicle_management.models.VehicleType;
import com.example.vehicle_management.services.ICardService;
import com.example.vehicle_management.services.ITicketTypeService;
import com.example.vehicle_management.services.IVehicleTypeService;

import java.util.List;
import java.util.stream.Collectors;

public class CardSwipeMapper {
    public static CardSwipeDTO toDTO(CardSwipe cardSwipe, ICardService cardService, IVehicleTypeService vehicleTypeService, ITicketTypeService ticketTypeService) {
        Card card = cardService.getCardById(cardSwipe.getCardId());
        String type = (card != null) ? card.getType() : "Không xác định";

        VehicleType vehicleType = vehicleTypeService.getVehicleTypeById(cardSwipe.getVehicleTypeId());
        String vehicleTypeName = (vehicleType != null) ? vehicleType.getVehicleTypeName() : "Không xác định";
        int vehicleTypeId = (vehicleType != null) ? vehicleType.getVehicleTypeId() :0;

        TicketType ticketType =ticketTypeService.getTicketTypeById(card.getCardId());
        int ticketTypeId = (ticketType != null) ? ticketType.getTicketTypeId() : 0;
        String ticketTypeName = (ticketType != null) ? ticketType.getTicketTypeName() : "";

        return new CardSwipeDTO(
                cardSwipe.getCardSwipeId(),
                cardSwipe.getLicensePlate(),
                cardSwipe.getCardId(),
                type,
                cardSwipe.getCheckInTime(),
                cardSwipe.getCheckOutTime(),
                cardSwipe.getCheckInImagePath(),
                cardSwipe.getImagePathOut(),
                cardSwipe.getPrice(),
                vehicleTypeName,
                vehicleTypeId,
                ticketTypeId,
                ticketTypeName
        );
    }

    public static List<CardSwipeDTO> toDTOList(List<CardSwipe> cardSwipes, ICardService cardService, IVehicleTypeService vehicleTypeService, ITicketTypeService ticketTypeService) {
        return cardSwipes.stream()
                .map(cs -> toDTO(cs, cardService, vehicleTypeService, ticketTypeService ))
                .collect(Collectors.toList());
    }
}
