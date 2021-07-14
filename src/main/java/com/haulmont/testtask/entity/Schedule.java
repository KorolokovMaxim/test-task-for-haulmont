package com.haulmont.testtask.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Schedule {

    private String  id;
    private String offerID;
    private LocalDate datePayment;
    private Double amountPaymentBody;
    private Double amountPayment;
    private Double amountPaymentPercent;

    public Schedule() {
    }

    public Double getAmountPaymentBody() {
        return amountPaymentBody;
    }

    public void setAmountPaymentBody(Double amountPaymentBody) {
        this.amountPaymentBody = amountPaymentBody;
    }

    public Double getAmountPayment() {
        return amountPayment;
    }

    public void setAmountPayment(Double amountPayment) {
        this.amountPayment = amountPayment;
    }

    public Double getAmountPaymentPercent() {
        return amountPaymentPercent;
    }

    public void setAmountPaymentPercent(Double amountPaymentPercent) {
        this.amountPaymentPercent = amountPaymentPercent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOfferID() {
        return offerID;
    }

    public void setOfferID(String offerID) {
        this.offerID = offerID;
    }

    public LocalDate getDatePayment() {
        return datePayment;
    }

    public void setDatePayment(LocalDate datePayment) {
        this.datePayment = datePayment;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return Objects.equals(id, schedule.id) && Objects.equals(offerID, schedule.offerID) && Objects.equals(datePayment, schedule.datePayment) && Objects.equals(amountPaymentBody, schedule.amountPaymentBody) && Objects.equals(amountPayment, schedule.amountPayment) && Objects.equals(amountPaymentPercent, schedule.amountPaymentPercent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, offerID, datePayment, amountPaymentBody, amountPayment, amountPaymentPercent);
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id='" + id + '\'' +
                ", offerID='" + offerID + '\'' +
                ", datePayment=" + datePayment +
                ", amountPaymentBody='" + amountPaymentBody + '\'' +
                ", amountPayment='" + amountPayment + '\'' +
                ", amountPaymentPercent='" + amountPaymentPercent + '\'' +
                '}';
    }
}
