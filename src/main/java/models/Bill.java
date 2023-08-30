package models;

import models.enums.StateBillEnum;
import models.enums.TypeBillEnum;
import utils.DateUtil;

import java.util.Date;

public class Bill {

    private Integer billNo;

    private TypeBillEnum typeBillEnum;

    private Double amount;

    private Date dueDate;

    private StateBillEnum stateBillEnum;

    private String provider;

    public Bill() {
    }

    public static Bill.BillBuilder builder() {
        return new Bill.BillBuilder();
    }

    public Bill(Integer billNo, TypeBillEnum typeBillEnum, Double amount,
                Date dueDate, StateBillEnum stateBillEnum, String provider) {
        this.billNo = billNo;
        this.typeBillEnum = typeBillEnum;
        this.amount = amount;
        this.dueDate = dueDate;
        this.stateBillEnum = stateBillEnum;
        this.provider = provider;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getBillNo() {
        return billNo;
    }

    public void setBillNo(Integer billNo) {
        this.billNo = billNo;
    }

    public TypeBillEnum getTypeBillEnum() {
        return typeBillEnum;
    }

    public void setTypeBillEnum(TypeBillEnum typeBillEnum) {
        this.typeBillEnum = typeBillEnum;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public StateBillEnum getStateBillEnum() {
        return stateBillEnum;
    }

    public void setStateBillEnum(StateBillEnum stateBillEnum) {
        this.stateBillEnum = stateBillEnum;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public static String title() {
        return String.format("%15s %15s %15s %15s %15s %15s", "Bill No",
                "Type",  "Amount", "Due Date",
                "State", "PROVIDER");
    }

    public String toString(int number) {
        return String.format("%15s %15s %15s %15s %15s %15s", number + 1, typeBillEnum,
                amount,  DateUtil.convertDateToString(dueDate), stateBillEnum,
                provider);
    }


    public static class BillBuilder {
        private Integer billNo;

        private TypeBillEnum typeBillEnum;

        private Double amount;

        private Date dueDate;

        private StateBillEnum stateBillEnum;

        private String provider;

        BillBuilder() {
        }

        public Bill.BillBuilder parse(String[] objects) {
            this.billNo = Integer.parseInt(objects[0]);
            this.typeBillEnum = TypeBillEnum.valueOf(objects[1]);
            this.amount = Double.parseDouble(objects[2]);
            this.dueDate = DateUtil.convertStringToDate(objects[3]);
            this.stateBillEnum = StateBillEnum.valueOf(objects[4]);
            this.provider = objects[5];
            return this;
        }

        public Bill.BillBuilder billNo(Integer billNo) {
            this.billNo = billNo;
            return this;
        }

        public Bill.BillBuilder typeBillEnum(TypeBillEnum typeBillEnum) {
            this.typeBillEnum = typeBillEnum;
            return this;
        }

        public Bill.BillBuilder amount(Double amount) {
            this.amount = amount;
            return this;
        }

        public Bill.BillBuilder dueDate(Date dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public Bill.BillBuilder stateBillEnum(StateBillEnum stateBillEnum) {
            this.stateBillEnum = stateBillEnum;
            return this;
        }

        public Bill.BillBuilder provider(String provider) {
            this.provider = provider;
            return this;
        }

        public Bill build() {
            return new Bill(billNo, typeBillEnum, amount, dueDate, stateBillEnum, provider);
        }
    }
}
