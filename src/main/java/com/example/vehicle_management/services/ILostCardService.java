package com.example.vehicle_management.services;

import com.example.vehicle_management.models.LostCard;

import java.util.List;

public interface ILostCardService {
    boolean addLostCard(LostCard lostCard);
    boolean updateLostCard(LostCard lostCard);
    boolean deleteLostCard(int id);
    LostCard getLostCardById(int id);
    List<LostCard> getAllLostCards();
}
