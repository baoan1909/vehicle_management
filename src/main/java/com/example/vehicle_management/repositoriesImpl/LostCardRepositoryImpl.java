package com.example.vehicle_management.repositoriesImpl;

import com.example.vehicle_management.models.LostCard;
import com.example.vehicle_management.repositories.ILostCardRepository;

import java.util.List;

public class LostCardRepositoryImpl implements ILostCardRepository {
    @Override
    public boolean insert(LostCard lostCard) {
        return false;
    }

    @Override
    public boolean update(LostCard lostCard) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public LostCard getById(int id) {
        return null;
    }

    @Override
    public List<LostCard> getAll() {
        return List.of();
    }
}
