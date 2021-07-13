package com.haulmont.testtask.entity;

import java.util.Objects;
import java.util.UUID;

public class Credit {

    private String UUIDStr = UUID.randomUUID().toString();
    private String id = UUIDStr;
    private String name;
    private String bankID;
    private String limit;
    private String interestRate;

    public Credit() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBankID() {
        return bankID;
    }

    public void setBankID(String bankID) {
        this.bankID = bankID;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(String interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Credit credit = (Credit) o;
        return Objects.equals(id, credit.id) && Objects.equals(name, credit.name) && Objects.equals(bankID, credit.bankID) && Objects.equals(limit, credit.limit) && Objects.equals(interestRate, credit.interestRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(UUIDStr, id, name, bankID, limit, interestRate);
    }

    @Override
    public String toString() {
        return "Credit{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", bankID='" + bankID + '\'' +
                ", limit='" + limit + '\'' +
                ", interestRate='" + interestRate + '\'' +
                '}';
    }
}
