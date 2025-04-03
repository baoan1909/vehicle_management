package com.example.vehicle_management.repositories;

import java.util.List;

public interface IRepository<T> {
    boolean insert(T t);
    boolean update(T t);
    boolean delete(int id);
    T getById(int id);
    List<T> getAll();
}
