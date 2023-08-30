package services.impl;

import constants.CharacterConstant;
import models.Bill;
import models.Customer;
import models.MessageError;
import models.Payment;
import models.enums.QuestionEnum;
import models.enums.StatusErrorEnum;
import services.AdapterService;
import services.QuestionService;
import utils.DateUtil;
import utils.StringFormatUtil;
import validators.BillValidator;
import validators.CustomerValidator;
import validators.MessageValidatorAdapter;
import validators.PaymentValidator;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static services.AdapterService.reader;
import static utils.StringFormatUtil.removeDuplicateNumber;

public class QuestionServiceImpl extends MessageValidatorAdapter implements QuestionService {
    @Override
    public QuestionEnum processQuestion1(String input) throws IOException {
        //Get List Menu
        print(CharacterConstant.SPACE_MENU);

        //Start Process Add Fund To Account
        print("Your Available Balance: " + AdapterService.getInstanceCustomerService().getCurrentCustomerLogin().getFund());
        print("Please Enter Fund:");
        if(Objects.isNull(input)){
            input = reader.readLine().trim();
        }else{
            print(input);
        }
        MessageError messageError =  CustomerValidator.validateFund(input);
        if(messageError.getStatusErrorEnum().equals(StatusErrorEnum.BAD_REQUEST)){
            printError(messageError);
            return QuestionEnum.QUESTION_1;
        }
        AdapterService.getInstanceCustomerService().addFund(Double.parseDouble(input));
        printError(messageError);
        return QuestionEnum.QUESTION_EXIT;
    }

    @Override
    public QuestionEnum processQuestion2() {
        //List Title
        print(Bill.title());

        //Print All List Bill
        List<Bill> list = AdapterService.getInstanceBillService().listBill();
        for (int i = 0; i < list.size(); i++) {
            print(list.get(i).toString(i));
        }
        return QuestionEnum.QUESTION_EXIT;
    }


    @Override
    public QuestionEnum processQuestion3(String input) throws IOException {
        print(CharacterConstant.SPACE_MENU);

        //Start Process Add Fund To Account
        print(AdapterService.getInstanceCustomerService().getCurrentCustomerLogin().toString());
        print("Your Available Balance: " + AdapterService.getInstanceCustomerService().getCurrentCustomerLogin().getFund());
        print("Please Enter Id Pay:");
        if(Objects.isNull(input)){
            input = reader.readLine().trim();
        }else{
            print(input);
        }
        String[] parseToArrayStringBillNos = StringFormatUtil.splitStrings(input);
        MessageError messageError =  BillValidator.validateProcessPay(parseToArrayStringBillNos);
        if(messageError.getStatusErrorEnum().equals(StatusErrorEnum.BAD_REQUEST)){
            printError(messageError);
            return QuestionEnum.QUESTION_3;
        }

        //excute logic and update
        int[] billNoInt = Arrays.stream(parseToArrayStringBillNos).mapToInt(Integer::parseInt).toArray();
        int n = parseToArrayStringBillNos.length;
        n = removeDuplicateNumber(billNoInt, n);
        List<Bill> resultBill = AdapterService.getInstanceBillService().findBillByArrayID(billNoInt);
        Double totalAmount = resultBill
                .stream()
                .map(Bill::getAmount)
                .collect(Collectors.summarizingDouble(Double::doubleValue))
                .getSum();
        Customer customer = AdapterService.getInstanceCustomerService().getCurrentCustomerLogin();
        customer.setFund(customer.getFund() - totalAmount);

        print("Payment has been completed for Bill with id " + Arrays.toString(parseToArrayStringBillNos));
        print("You current balance is: " + customer.getFund());
        printError(messageError);
        return QuestionEnum.QUESTION_EXIT;
    }

    @Override
    public QuestionEnum processQuestion4() throws IOException {
        return null;
    }

    @Override
    public QuestionEnum processQuestion5(String input) throws IOException {
        print(CharacterConstant.SPACE_MENU);

        print("Please Enter Schedule:");
        if(Objects.isNull(input)){
            input = reader.readLine().trim();
        }else{
            print(input);
        }
        String[] parseToArrayStringBillNos = StringFormatUtil.splitStrings(input);
        MessageError messageError =  PaymentValidator.validateSchedulePayment(parseToArrayStringBillNos);
        if(messageError.getStatusErrorEnum().equals(StatusErrorEnum.BAD_REQUEST)){
            printError(messageError);
            return QuestionEnum.QUESTION_5;
        }

        //excute logic and update
        AdapterService.getInstancePaymentService().addPaymentForCurrentLoginCustomer(parseToArrayStringBillNos[0], DateUtil.convertStringToDate(parseToArrayStringBillNos[1]));
        printError(messageError);
        return QuestionEnum.QUESTION_EXIT;
    }

    @Override
    public QuestionEnum processQuestion6() {
        //List Title
        print(Payment.title());

        //Print All List Bill
        List<Payment> list = AdapterService.getInstancePaymentService().listPayment();
        for (int i = 0; i < list.size(); i++) {
            print(list.get(i).toString(i));
        }
        return QuestionEnum.QUESTION_EXIT;
    }

    @Override
    public QuestionEnum processQuestion7(String input) throws IOException {
        print(CharacterConstant.SPACE_MENU);

        //Start Process Add Fund To Account
        print(AdapterService.getInstanceCustomerService().getCurrentCustomerLogin().toString());
        print("Search Bill By Provider:");
        if(Objects.isNull(input)){
            input = reader.readLine().trim();
        }else{
            print(input);
        }
        MessageError messageError =  BillValidator.validateSearchProvider(input);
        if(messageError.getStatusErrorEnum().equals(StatusErrorEnum.BAD_REQUEST)){
            printError(messageError);
            return QuestionEnum.QUESTION_7;
        }

        //excute logic and update
        for (Bill bill : AdapterService.getInstanceBillService().listBill()) {
            if(input.equalsIgnoreCase(bill.getProvider())){
                print(bill.toString());
            }
        }
        printError(messageError);
        return QuestionEnum.QUESTION_EXIT;
    }

    @Override
    public  void processQuestion(QuestionEnum questionEnum) throws IOException {
        switch (questionEnum){
            case QUESTION_1:
                questionEnum = processQuestion1(null);
                break;
            case QUESTION_2:
                questionEnum = processQuestion2();
                break;
            case QUESTION_3:
                questionEnum = processQuestion3(null);
                break;
//            TODO Development later
            case QUESTION_4:
                print("Development later");
                break;
            case QUESTION_5:
                questionEnum = processQuestion5(null);
                break;
            case QUESTION_6:
                questionEnum = processQuestion6();
                break;
            case QUESTION_7:
                questionEnum = processQuestion7(null);
                break;
            case QUESTION_EXIT:
                break;
            default:
                print("Not Found Question");
                break;
        }
    }
}
