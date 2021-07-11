package com.haulmont.testtask.dao;
import java.util.List;
public interface CrudDAO<T, U>{


    void createOne(T object);

    List<T> getAll();

    T findById(U id);

    void updateOne(T object);

    void deleteOne(T object);
}
