package com.haulmont.testtask.dao;
import com.haulmont.testtask.entity.Bank;

import java.util.List;
public interface BankDAO{


    void createOne(Bank bank);

    List<Bank> getAll();

    Bank findById(String id);

    void updateOne(Bank bank);

    void deleteOne(Bank bank);
}
