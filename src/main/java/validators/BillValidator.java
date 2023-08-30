package validators;

import constants.CommonRegexConstant;
import models.Bill;
import models.MessageError;
import models.enums.TypeMessageEnum;
import services.AdapterService;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static utils.StringFormatUtil.removeDuplicateNumber;

public class BillValidator extends MessageValidatorAdapter {

    public static MessageError validateProcessPay(String[] billNo){
        //check valid number
        for (String billNoId : billNo) {
            if(Objects.isNull(billNoId) || !billNoId.matches(CommonRegexConstant.REGEX_NUMBER)){
                return buildMessageError(TypeMessageEnum.ERROR_BILL_INVALID);
            }
            Bill bill = AdapterService.getInstanceBillService().findBillById(Integer.parseInt(billNoId));
            if(Objects.isNull(bill)
            ){
                return buildMessageError(TypeMessageEnum.ERROR_BILL_NOT_EXISTS);
            }
        }

        //Check amount and fund
        int[] billNoInt = Arrays.stream(billNo).mapToInt(Integer::parseInt).toArray();
        int n = billNo.length;
        n = removeDuplicateNumber(billNoInt, n);
        List<Bill> resultBill = AdapterService.getInstanceBillService().findBillByArrayID(billNoInt);
        Double totalAmount = resultBill
                .stream()
                .map(Bill::getAmount)
                .collect(Collectors.summarizingDouble(Double::doubleValue))
                .getSum();
        if(AdapterService.getInstanceCustomerService().getCurrentCustomerLogin().getFund() < totalAmount){
            return buildMessageError(TypeMessageEnum.ERROR_BILL_PAY);
        }
        return buildMessageSuccess();
    }

    public static MessageError validateSearchProvider(String provider){
        if(provider.isEmpty()){
            return buildMessageError(TypeMessageEnum.ERROR_PROVIDER_INVALID);
        }
        return buildMessageSuccess();
    }

}
