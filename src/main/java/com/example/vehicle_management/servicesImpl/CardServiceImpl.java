package com.example.vehicle_management.servicesImpl;

import com.example.vehicle_management.models.Card;
import com.example.vehicle_management.repositories.ICardRepository;
import com.example.vehicle_management.services.ICardService;

import java.util.List;

public class CardServiceImpl implements ICardService {
    private final ICardRepository cardRepository;
    public CardServiceImpl(ICardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public boolean insertCard(Card card) {
        return cardRepository.insert(card);
    }

    @Override
    public boolean updateCard(Card card) {
        return cardRepository.update(card);
    }

    @Override
    public boolean deleteCard(int id) {
        return cardRepository.delete(id);
    }

    @Override
    public Card getCardById(int id) {
        return cardRepository.getById(id);
    }

    @Override
    public List<Card> getAllCards() {
        return cardRepository.getAll();
    }

    @Override
    public boolean isExistsCardNumber(String cardNumber) {
        return cardRepository.existsByCardNumber(cardNumber);
    }

    @Override
    public double getParkingFeeVisitorByCardId(int cardId) {
        return cardRepository.getParkingFeeVisitorByCardId(cardId);
    }

    @Override
    public int getCustomerIdByCardId(int cardId) {
        return cardRepository.getCustomerIdByCardId(cardId);
    }

    @Override
    public int getCardIdByCardNumber(String cardNumber) {
        return cardRepository.getCardIdByCardNumber(cardNumber);
    }

    @Override
    public List<Card> getCardIdByType() {
        return cardRepository.getCardIdByType();
    }


}
