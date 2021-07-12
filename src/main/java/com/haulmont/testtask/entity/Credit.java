package com.haulmont.testtask.entity;

import java.util.Objects;
import java.util.UUID;

public class Credit {
    String UUIDStr = UUID.randomUUID().toString();
    private String id = UUIDStr;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private String bankID;
    private float limit;
    private float interestRate;

    public Credit() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBankID() {
        return bankID;
    }


    public void setBankID(String bankID) {
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
        return Float.compare(credit.limit, limit) == 0 && Float.compare(credit.interestRate, interestRate) == 0 && Objects.equals(UUIDStr, credit.UUIDStr) && Objects.equals(id, credit.id) && Objects.equals(name, credit.name) && Objects.equals(bankID, credit.bankID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(UUIDStr, id, name, bankID, limit, interestRate);
    }

    @Override
    public String toString() {
        return "Credit{" +
                "UUIDStr='" + UUIDStr + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", bankID='" + bankID + '\'' +
                ", limit=" + limit +
                ", interestRate=" + interestRate +
                '}';
    }
}
