package com.haulmont.testtask.dao;

import com.haulmont.testtask.entity.Credit;

import java.util.List;

public interface CreditDAO {

    void createOne(Credit bank);

    List<Credit> getAll();

    Credit findById(String id);

    void updateOne(Credit bank);

    void deleteOne(Credit bank);

    String getBank(String id);

}
