package com.haulmont.testtask.entity;

import java.util.Objects;
import java.util.UUID;

public class Client {
    String UUIDStr = UUID.randomUUID().toString();
    private String id = UUIDStr;
    private String FIO;
    private String bankID;
    private String phoneNumber;
    private String email;
    private String passport;

    public Client() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public String getBankID() {
        return bankID;
    }

    public void setBankID(String bankID) {
        this.bankID = bankID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) &&
                Objects.equals(FIO, client.FIO) &&
                Objects.equals(bankID, client.bankID) &&
                Objects.equals(phoneNumber, client.phoneNumber) &&
                Objects.equals(email, client.email) &&
                Objects.equals(passport, client.passport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, FIO, bankID, phoneNumber, email, passport);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", FIO='" + FIO + '\'' +
                ", bankID=" + bankID +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", passport='" + passport + '\'' +
                '}';
    }
}
