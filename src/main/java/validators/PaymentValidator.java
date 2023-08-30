package validators;

import constants.RegexConstant;
import models.MessageError;
import models.enums.TypeMessageEnum;
import services.AdapterService;

import java.util.Objects;

public class PaymentValidator extends MessageValidatorAdapter {

    public static MessageError validateSchedulePayment(String[] input){
        int number;
        if(input.length < 2){
            return buildMessageError(TypeMessageEnum.ERROR_PAYMENT_INVALID);
        }
        String idBill = input[0];
        String dateSchedule = input[1];
        if (!idBill.matches(RegexConstant.REGEX_NUMBER)) {
            return buildMessageError(TypeMessageEnum.ERROR_PAYMENT_INVALID);
        }
        number = Integer.parseInt(idBill);
        if (number < 0) {
            return buildMessageError(TypeMessageEnum.ERROR_PAYMENT_INVALID);
        }
        if (!dateSchedule.matches(RegexConstant.REGEX_DATE)) {
            return buildMessageError(TypeMessageEnum.ERROR_PAYMENT_INVALID);
        }
        if (!Objects.isNull(AdapterService.getInstancePaymentService().findPaymentByBillId(Integer.parseInt(idBill)))) {
            return buildMessageError(TypeMessageEnum.ERROR_PAYMENT_EXISTS);
        }
        return buildMessageSuccess();
    }


}
