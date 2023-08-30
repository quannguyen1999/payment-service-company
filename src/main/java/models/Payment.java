package models;

import models.enums.StatePaymentEnum;
import utils.DateUtil;

import java.util.Date;

public class Payment {

    private Integer paymentId;

    private Double amount;

    private Date paymentDate;

    private StatePaymentEnum statePaymentEnum;

    private Integer billId;

    public Payment(Integer paymentId, Double amount, Date paymentDate, StatePaymentEnum statePaymentEnum, Integer billId) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.statePaymentEnum = statePaymentEnum;
        this.billId = billId;
    }

    public Payment() {
    }

    public static PaymentBuilder builder() {
        return new PaymentBuilder();
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public StatePaymentEnum getStatePaymentEnum() {
        return statePaymentEnum;
    }

    public void setStatePaymentEnum(StatePaymentEnum statePaymentEnum) {
        this.statePaymentEnum = statePaymentEnum;
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public static String title() {
        return String.format("%15s %15s %15s %15s %15s", "No",
                "Amount", "Payment Date",
                "State", "Bill Id");
    }

    public String toString(int number) {
        return String.format("%15s %15s %15s %15s %15s", number + 1, amount,
                DateUtil.convertDateToString(paymentDate), statePaymentEnum, billId);
    }

    public static class PaymentBuilder {
        private Integer paymentId;
        private Double amount;
        private Date paymentDate;
        private StatePaymentEnum statePaymentEnum;
        private Integer billId;

        PaymentBuilder() {
        }

        public PaymentBuilder paymentId(Integer paymentId) {
            this.paymentId = paymentId;
            return this;
        }

        public PaymentBuilder amount(Double amount) {
            this.amount = amount;
            return this;
        }

        public PaymentBuilder paymentDate(Date paymentDate) {
            this.paymentDate = paymentDate;
            return this;
        }

        public PaymentBuilder statePaymentEnum(StatePaymentEnum statePaymentEnum) {
            this.statePaymentEnum = statePaymentEnum;
            return this;
        }

        public PaymentBuilder billId(Integer billId) {
            this.billId = billId;
            return this;
        }

        public Payment build() {
            return new Payment(paymentId, amount, paymentDate, statePaymentEnum, billId);
        }

        public String toString() {
            return "Payment.PaymentBuilder(paymentId=" + this.paymentId + ", amount=" + this.amount + ", paymentDate=" + this.paymentDate + ", statePaymentEnum=" + this.statePaymentEnum + ", billId=" + this.billId + ")";
        }
    }
}
