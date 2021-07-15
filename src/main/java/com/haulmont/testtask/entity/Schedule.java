package com.haulmont.testtask.entity;

import java.util.Objects;

public class Schedule {

    private String datePayment;
    private String amountPaymentBody;
    private String amountPayment;
    private String amountPaymentPercent;
    private String totalPayments;


    public Schedule() {
    }

    public String getTotalPayments() {
        return totalPayments;
    }

    public void setTotalPayments(String totalPayments) {
        this.totalPayments = totalPayments;
    }

    public String getAmountPaymentBody() {
        return amountPaymentBody;
    }

    public void setAmountPaymentBody(String amountPaymentBody) {
        this.amountPaymentBody = amountPaymentBody;
    }

    public String getAmountPayment() {
        return amountPayment;
    }

    public void setAmountPayment(String amountPayment) {
        this.amountPayment = amountPayment;
    }

    public String getAmountPaymentPercent() {
        return amountPaymentPercent;
    }

    public void setAmountPaymentPercent(String amountPaymentPercent) {
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
