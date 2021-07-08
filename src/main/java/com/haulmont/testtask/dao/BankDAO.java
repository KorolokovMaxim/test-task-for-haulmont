package com.haulmont.testtask.dao;

import com.haulmont.testtask.entity.Bank;

import java.util.List;

public interface BankDAO {

    void add(Bank bank);

    List<Bank> getAll();

    Bank findById(Long id);

    void update(Bank bank);

    void delete(Bank bank);

}
