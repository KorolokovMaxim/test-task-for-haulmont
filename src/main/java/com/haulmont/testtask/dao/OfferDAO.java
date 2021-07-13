package com.haulmont.testtask.dao;

import com.haulmont.testtask.entity.Offer;

import java.util.List;

public interface OfferDAO {

    void createOne(Offer offer);

    List<Offer> getAll();

    Offer findById(String id);

    void updateOne(Offer offer);

    void deleteOne(Offer offer);

    String getClient(String id);

    String getCredit(String id);
}
