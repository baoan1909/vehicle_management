package com.example.vehicle_management.servicesImpl;

import com.example.vehicle_management.models.Card;
import com.example.vehicle_management.services.ICardService;

import java.util.List;

public class CardServiceImpl implements ICardService {
    @Override
    public boolean insertCard(Card card) {
        return false;
    }

    @Override
    public boolean updateCard(Card card) {
        return false;
    }

    @Override
    public boolean deleteCard(int id) {
        return false;
    }

    @Override
    public Card getCardById(int id) {
        return null;
    }

    @Override
    public List<Card> getAllCards() {
        return List.of();
    }
}
