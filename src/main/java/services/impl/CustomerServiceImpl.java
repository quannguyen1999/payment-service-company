package services.impl;

import constants.CharacterConstant;
import models.Customer;
import services.CustomerService;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

public class CustomerServiceImpl implements CustomerService {
    private ArrayList<Customer> customerArrayList = null;

    private Customer currentLoginCustomer = null;

    @Override
    public void initListDataCustomer() {
        if(Objects.isNull(customerArrayList)){
            customerArrayList = new ArrayList<>();
            scanner.useDelimiter(CharacterConstant.COMMA);
            while (scanner.hasNext()) {
                customerArrayList.add(Customer.builder().parse(scanner.nextLine().split(CharacterConstant.COMMA)).build());
            }
            scanner.close();
        }
    }

    @Override
    public ArrayList<Customer> getListCustomer() {
        return customerArrayList;
    }

    @Override
    public Boolean addFund(Double money) {
        Customer customer = getCurrentCustomerLogin();
        customer.setFund(customer.getFund() + money);
        return Boolean.TRUE;
    }

    @Override
    public Customer getCurrentCustomerLogin() {
        return currentLoginCustomer;
    }

    @Override
    public void removeCurrentCustomerLogin() {
       currentLoginCustomer = null;
    }

    @Override
    public void setCurrentCustomerLogin(Customer customer) {
        currentLoginCustomer = customer;
    }

    @Override
    public Customer findCustomerById(Integer id) {
        return customerArrayList.stream()
                .filter(t->t.getId().equals(id))
                .collect(Collectors.toList())
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Customer findByAccount(String account) {
        return customerArrayList.stream()
                .filter(t->t.getAccount().equalsIgnoreCase(account))
                .collect(Collectors.toList())
                .stream()
                .findFirst()
                .orElse(null);
    }


}
