package services.impl;

import models.Bill;
import models.Customer;
import models.Payment;
import models.enums.StatePaymentEnum;
import services.AdapterService;
import services.PaymentService;

import java.util.Date;
import java.util.List;

public class PaymentServiceImpl implements PaymentService {

    @Override
    public List<Payment> listPayment() {
        return AdapterService.getInstanceCustomerService().getCurrentCustomerLogin().getPayments();
    }

    @Override
    public Payment findPaymentByBillId(Integer id) {
        return AdapterService.getInstanceCustomerService().getCurrentCustomerLogin().getPayments()
                .stream().filter(t->t.getBillId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void addPaymentForCurrentLoginCustomer(String idBill, Date dueDate) {
        Bill bill = AdapterService.getInstanceBillService().findBillById(Integer.parseInt(idBill));
        Customer currentCustomer = AdapterService.getInstanceCustomerService().getCurrentCustomerLogin();
        currentCustomer.getPayments().add(Payment.builder()
                .amount(bill.getAmount())
                .paymentDate(dueDate)
                .statePaymentEnum(StatePaymentEnum.PENDING)
                .billId(bill.getBillNo())
                .build());
    }

}
