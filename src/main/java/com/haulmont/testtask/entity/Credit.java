package com.haulmont.testtask.entity;

import java.util.Objects;

public class Credit {

    private Long id;
    private Long bankID;
    private float limit;
    private float interestRate;

    public Credit() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBankID() {
        return bankID;
    }

    public void setBankID(Long bankID) {
        this.bankID = bankID;
    }

    public float getLimit() {
        return limit;
    }

    public void setLimit(float limit) {
        this.limit = limit;
    }

    public float getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(float interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Credit credit = (Credit) o;
        return Float.compare(credit.limit, limit) == 0 &&
                Float.compare(credit.interestRate, interestRate) == 0 &&
                Objects.equals(id, credit.id) &&
                Objects.equals(bankID, credit.bankID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bankID, limit, interestRate);
    }

    @Override
    public String toString() {
        return "Credit{" +
                "id=" + id +
                ", bankID=" + bankID +
                ", limit=" + limit +
                ", interestRate=" + interestRate +
                '}';
    }
}
