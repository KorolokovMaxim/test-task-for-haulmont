package com.haulmont.testtask.dao;

import com.haulmont.testtask.entity.Bank;
import com.haulmont.testtask.entity.Client;

import java.util.List;

public interface ClientDAO{

    String getBank(String id);

    void createOne(Client client);

    List<Client> getAll();

    Client findById(String id);

    void updateOne(Client client);

    void deleteOne(Client client);


}
