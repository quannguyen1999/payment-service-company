package models;

import java.util.ArrayList;

public class Customer {

    private Integer id;

    private String name;

    private Double fund;

    private String account;

    private String password;

    private ArrayList<Payment> payments = new ArrayList<>();

    public Customer() {
    }

    public Customer(String[] objects){
        Customer.builder()
                    .id(Integer.parseInt(objects[0]))
                    .name(objects[1])
                    .fund(Double.parseDouble(objects[2]))
                    .account(objects[3])
                    .password(objects[4])
                    .build();
    }

    public static CustomerBuilder builder() {
        return new CustomerBuilder();
    }

    public Customer(Integer id, String name, Double fund, String account, String password) {
        this.id = id;
        this.name = name;
        this.fund = fund;
        this.account = account;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getFund() {
        return fund;
    }

    public void setFund(Double fund) {
        this.fund = fund;
    }

    public ArrayList<Payment> getPayments() {
        return payments;
    }

    public void setPayments(ArrayList<Payment> payments) {
        this.payments = payments;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "<< Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", fund=" + fund +
                ", payments=" + payments +
                "} >>";
    }

    public static class CustomerBuilder {
        private Integer id;
        private String name;
        private Double fund;

        private String account;

        private String password;

        CustomerBuilder() {
        }

        public CustomerBuilder parse(String[] objects) {
            this.id = Integer.parseInt(objects[0]);
            this.name = objects[1];
            this.fund = Double.parseDouble(objects[2]);
            this.account =  objects[3];
            this.password =  objects[4];
            return this;
        }

        public CustomerBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public CustomerBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CustomerBuilder fund(Double fund) {
            this.fund = fund;
            return this;
        }

        public CustomerBuilder account(String account) {
            this.account = account;
            return this;
        }

        public CustomerBuilder password(String password) {
            this.password = password;
            return this;
        }

        public Customer build() {
            return new Customer(id, name, fund, account, password);
        }

        public String toString() {
            return "Customer.CustomerBuilder(id=" + this.id + ", name=" + this.name + ", fund=" + this.fund;
        }
    }


}
