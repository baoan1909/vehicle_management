package com.example.vehicle_management.servicesImpl;

import com.example.vehicle_management.models.CardSwipe;
import com.example.vehicle_management.repositories.ICardSwipeRepository;
import com.example.vehicle_management.services.ICardSwipeService;

import java.util.List;

public class CardSwipeServiceImpl implements ICardSwipeService {
    private final ICardSwipeRepository cardSwipeRepository;
    public CardSwipeServiceImpl(ICardSwipeRepository cardSwipeRepository) {
        this.cardSwipeRepository = cardSwipeRepository;
    }

    @Override
    public boolean insertCardSwipe(CardSwipe cardSwipe) {
        return false;
    }

    @Override
    public boolean updateCardSwipe(CardSwipe cardSwipe) {
        return false;
    }

    @Override
    public boolean deleteCardSwipe(int id) {
        return false;
    }

    @Override
    public CardSwipe getCardSwipeById(int id) {
        return null;
    }

    @Override
    public List<CardSwipe> getAllCardSwipes() {
        return List.of();
    }

    @Override
    public CardSwipe getCardSwipeByCardId(int cardId) {
        return cardSwipeRepository.getCardSwipeByCardId(cardId);
    }
}
