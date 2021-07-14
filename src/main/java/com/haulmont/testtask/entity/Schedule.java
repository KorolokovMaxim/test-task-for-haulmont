package com.haulmont.testtask.entity;

import java.util.Objects;

public class Schedule {

    private String datePayment;
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


    public String getDatePayment() {
        return datePayment;
    }

    public void setDatePayment(String datePayment) {
        this.datePayment = datePayment;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return   Objects.equals(datePayment, schedule.datePayment) && Objects.equals(amountPaymentBody, schedule.amountPaymentBody) && Objects.equals(amountPayment, schedule.amountPayment) && Objects.equals(amountPaymentPercent, schedule.amountPaymentPercent);
    }

    @Override
    public int hashCode() {
        return Objects.hash( datePayment, amountPaymentBody, amountPayment, amountPaymentPercent);
    }

    @Override
    public String toString() {
        return "Schedule{" +
                ", datePayment=" + datePayment +
                ", amountPaymentBody='" + amountPaymentBody + '\'' +
                ", amountPayment='" + amountPayment + '\'' +
                ", amountPaymentPercent='" + amountPaymentPercent + '\'' +
                '}';
    }
}
