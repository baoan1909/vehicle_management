package com.example.vehicle_management.servicesImpl;

import com.example.vehicle_management.models.LostCard;
import com.example.vehicle_management.services.ILostCardService;

import java.util.List;

public class LostCardServiceImpl implements ILostCardService {
    @Override
    public boolean addLostCard(LostCard lostCard) {
        return false;
    }

    @Override
    public boolean updateLostCard(LostCard lostCard) {
        return false;
    }

    @Override
    public boolean deleteLostCard(int id) {
        return false;
    }

    @Override
    public LostCard getLostCardById(int id) {
        return null;
    }

    @Override
    public List<LostCard> getAllLostCards() {
        return List.of();
    }
}
