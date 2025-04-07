package com.example.vehicle_management.services;

import com.example.vehicle_management.models.CardSwipe;

import java.util.List;

public interface ICardSwipeService {
    boolean insertCardSwipe(CardSwipe cardSwipe);
    boolean updateCardSwipe(CardSwipe cardSwipe);
    boolean deleteCardSwipe(int id);
    CardSwipe getCardSwipeById(int id);
    List<CardSwipe> getAllCardSwipes();
    CardSwipe getCardSwipeByCardId(int cardId);
}
