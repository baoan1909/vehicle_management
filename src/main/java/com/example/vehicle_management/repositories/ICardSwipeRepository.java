package com.example.vehicle_management.repositories;

import com.example.vehicle_management.models.CardSwipe;

public interface ICardSwipeRepository extends IRepository<CardSwipe> {
    CardSwipe getCardSwipeByCardId(int cardId);
}
