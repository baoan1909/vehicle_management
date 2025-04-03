package com.example.vehicle_management.services;

import com.example.vehicle_management.models.Card;

import java.util.List;

public interface ICardService {
    boolean insertCard(Card card);
    boolean updateCard(Card card);
    boolean deleteCard(int id);
    Card getCardById(int id);
    List<Card> getAllCards();
}
