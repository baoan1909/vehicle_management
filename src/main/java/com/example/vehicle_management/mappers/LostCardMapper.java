package com.example.vehicle_management.mappers;


import com.example.vehicle_management.dtos.LostCardDTO;
import com.example.vehicle_management.models.Card;
import com.example.vehicle_management.models.Customer;
import com.example.vehicle_management.models.LostCard;
import com.example.vehicle_management.services.ICardService;
import com.example.vehicle_management.services.ICustomerService;

import java.util.List;
import java.util.stream.Collectors;

public class LostCardMapper {
    public static LostCardDTO toDTO(LostCard lostCard, ICardService cardService, ICustomerService customerService) {
        Card card = cardService.getCardById(lostCard.getCardId());
        Customer customer = customerService.getCustomerById(lostCard.getCustomerId());
        String cardNumber = (card != null) ? card.getCardNumber() : "Không xác định";
        String customerName = (customer != null ) ? customer.getFullName() : "Không xác định";

        // Tạo CardDTO
        return new LostCardDTO(
                lostCard.getLostCardId(),
                customerName,
                cardNumber,
                lostCard.getNotificationTime(),
                lostCard.getTimeOfLost(),
                lostCard.getTicketPrice(),
                lostCard.getLostCardFee(),
                lostCard.getCheckInLicensePhoto(),
                lostCard.getCheckInCustomerPhoto(),
                lostCard.getVisitorName(),
                lostCard.getVisitorPhoneNum(),
                lostCard.getIdentifyCard(),
                lostCard.getRegistrationLicense(),
                lostCard.getNote()
        );
    }

    public static List<LostCardDTO> toDTOList(List<LostCard> lostCards, ICardService cardService, ICustomerService customerService) {
        return lostCards.stream()
                .map(lostCard -> toDTO(lostCard, cardService, customerService))
                .collect(Collectors.toList());
    }
}
