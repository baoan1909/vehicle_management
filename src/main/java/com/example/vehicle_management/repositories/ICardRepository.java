package com.example.vehicle_management.repositories;

import com.example.vehicle_management.models.Card;

import java.util.List;

public interface ICardRepository extends IRepository<Card> {
    boolean existsByCardNumber(String cardNumber);
    double getParkingFeeVisitorByCardId(int cardId);
    int getCustomerIdByCardId(int cardId);
    int getCardIdByCardNumber(String cardNumber);
    List<Card> getCardIdByType();
}
