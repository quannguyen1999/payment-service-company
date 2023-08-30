package services;

import models.Payment;

import java.util.Date;
import java.util.List;

public interface PaymentService {
    List<Payment> listPayment();

    Payment findPaymentByBillId(Integer id);

    void addPaymentForCurrentLoginCustomer(String idBill, Date dueDate);



}
