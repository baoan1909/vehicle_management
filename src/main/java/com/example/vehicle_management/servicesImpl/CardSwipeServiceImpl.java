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
        return cardSwipeRepository.insert(cardSwipe);
    }

    @Override
    public boolean updateCardSwipe(CardSwipe cardSwipe) {
        return cardSwipeRepository.update(cardSwipe);
    }

    @Override
    public boolean deleteCardSwipe(int id) {
        return cardSwipeRepository.delete(id);
    }

    @Override
    public CardSwipe getCardSwipeById(int id) {
        return cardSwipeRepository.getById(id);
    }

    @Override
    public List<CardSwipe> getAllCardSwipes() {
        return cardSwipeRepository.getAll();
    }

    @Override
    public CardSwipe getCardSwipeByCardId(int cardId) {
        return cardSwipeRepository.getCardSwipeByCardId(cardId);
    }
}
