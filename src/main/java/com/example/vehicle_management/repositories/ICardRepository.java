package com.example.vehicle_management.repositories;

import com.example.vehicle_management.models.Card;

public interface ICardRepository extends IRepository<Card> {
    boolean existsByCardNumber(String cardNumber);
}
