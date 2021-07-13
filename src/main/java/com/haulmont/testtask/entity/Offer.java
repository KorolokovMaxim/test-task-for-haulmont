package com.haulmont.testtask.entity;

import java.util.Objects;
import java.util.UUID;

public class Offer {

    private String UUIDStr = UUID.randomUUID().toString();
    private String id = UUIDStr;
    private String clientID;
    private String creditID;
    private String creditAmount;

    public Offer() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getCreditID() {
        return creditID;
    }

    public void setCreditID(String creditID) {
        this.creditID = creditID;
    }

    public String getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(String creditAmount) {
        this.creditAmount = creditAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offer offer = (Offer) o;
        return Objects.equals(id, offer.id) && Objects.equals(clientID, offer.clientID) && Objects.equals(creditID, offer.creditID) && Objects.equals(creditAmount, offer.creditAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientID, creditID, creditAmount);
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id='" + id + '\'' +
                ", clientID='" + clientID + '\'' +
                ", creditID='" + creditID + '\'' +
                ", creditAmount='" + creditAmount + '\'' +
                '}';
    }
}
