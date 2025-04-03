package com.example.vehicle_management.repositoriesImpl;

import com.example.vehicle_management.models.Card;
import com.example.vehicle_management.repositories.ICardRepository;

import java.util.List;

public class CardRepositoryImpl implements ICardRepository {

    @Override
    public boolean insert(Card card) {
        return false;
    }

    @Override
    public boolean update(Card card) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Card getById(int id) {
        return null;
    }

    @Override
    public List<Card> getAll() {
        return List.of();
    }
}
