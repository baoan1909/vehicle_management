package com.example.vehicle_management.repositoriesImpl;

import com.example.vehicle_management.models.CardSwipe;
import com.example.vehicle_management.repositories.ICardSwipeRepository;

import java.util.List;

public class CardSwipeRepositoryImpl implements ICardSwipeRepository {

    @Override
    public boolean insert(CardSwipe cardSwipe) {
        return false;
    }

    @Override
    public boolean update(CardSwipe cardSwipe) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public CardSwipe getById(int id) {
        return null;
    }

    @Override
    public List<CardSwipe> getAll() {
        return List.of();
    }
}
