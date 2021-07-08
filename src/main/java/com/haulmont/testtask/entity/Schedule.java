package com.haulmont.testtask.entity;

import java.util.Date;
import java.util.Objects;

public class Schedule {

    private Long id;
    private Long offerID;
    private Date datePayment;
    private float amountPaymentBody;
    private float amountPayment;
    private float amountPaymentPercent;

    public Schedule() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOfferID() {
        return offerID;
    }

    public void setOfferID(Long offerID) {
        this.offerID = offerID;
    }

    public Date getDatePayment() {
        return datePayment;
    }

    public void setDatePayment(Date datePayment) {
        this.datePayment = datePayment;
    }

    public float getAmountPaymentBody() {
        return amountPaymentBody;
    }

    public void setAmountPaymentBody(float amountPaymentBody) {
        this.amountPaymentBody = amountPaymentBody;
    }

    public float getAmountPayment() {
        return amountPayment;
    }

    public void setAmountPayment(float amountPayment) {
        this.amountPayment = amountPayment;
    }

    public float getAmountPaymentPercent() {
        return amountPaymentPercent;
    }

    public void setAmountPaymentPercent(float amountPaymentPercent) {
        this.amountPaymentPercent = amountPaymentPercent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return Float.compare(schedule.amountPaymentBody, amountPaymentBody) == 0 &&
                Float.compare(schedule.amountPayment, amountPayment) == 0 &&
                Float.compare(schedule.amountPaymentPercent, amountPaymentPercent) == 0 &&
                Objects.equals(id, schedule.id) &&
                Objects.equals(offerID, schedule.offerID) &&
                Objects.equals(datePayment, schedule.datePayment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, offerID, datePayment, amountPaymentBody, amountPayment, amountPaymentPercent);
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", offerID=" + offerID +
                ", datePayment=" + datePayment +
                ", amountPaymentBody=" + amountPaymentBody +
                ", amountPayment=" + amountPayment +
                ", amountPaymentPercent=" + amountPaymentPercent +
                '}';
    }
}
