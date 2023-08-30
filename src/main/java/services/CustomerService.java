package services;

import models.Customer;
import models.enums.FilePathDataEnum;
import utils.ScannerFactoryUtil;

import java.util.ArrayList;
import java.util.Scanner;

public interface CustomerService {
    Scanner scanner = ScannerFactoryUtil.getScanner(FilePathDataEnum.PATH_DATA_CSV_CUSTOMER);

    void initListDataCustomer();

    ArrayList<Customer> getListCustomer();

    Boolean addFund(Double money);

    Customer getCurrentCustomerLogin();

    void removeCurrentCustomerLogin();

    Customer findCustomerById(Integer id);

    Customer findByAccount(String account);

    void setCurrentCustomerLogin(Customer customer);

}
