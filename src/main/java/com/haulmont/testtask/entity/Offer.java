package com.haulmont.testtask.entity;

import java.util.Objects;

public class Offer {

    private Long id;
    private Long clientID;
    private Long creditID;
    private float creditAmount;

    public Offer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientID() {
        return clientID;
    }

    public void setClientID(Long clientID) {
        this.clientID = clientID;
    }

    public Long getCreditID() {
        return creditID;
    }

    public void setCreditID(Long creditID) {
        this.creditID = creditID;
    }

    public float getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(float creditAmount) {
        this.creditAmount = creditAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offer offer = (Offer) o;
        return Float.compare(offer.creditAmount, creditAmount) == 0 &&
                Objects.equals(id, offer.id) &&
                Objects.equals(clientID, offer.clientID) &&
                Objects.equals(creditID, offer.creditID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientID, creditID, creditAmount);
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", clientID=" + clientID +
                ", creditID=" + creditID +
                ", creditAmount=" + creditAmount +
                '}';
    }
}
