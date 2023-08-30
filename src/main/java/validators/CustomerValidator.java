package validators;

import constants.CommonRegexConstant;
import models.Customer;
import models.MessageError;
import models.enums.TypeMessageEnum;
import services.AdapterService;
import services.CustomerService;

import java.util.Objects;

public class CustomerValidator extends MessageValidatorAdapter {
    private static CustomerService customerService = AdapterService.getInstanceCustomerService();



    public static MessageError validateFund(String fund){
        if(!fund.matches(CommonRegexConstant.REGEX_NUMBER) || Double.parseDouble(fund) < 0){
            return buildMessageError(TypeMessageEnum.ERROR_FUND_INVALID);
        }
        return buildMessageSuccess();
    }



    public static MessageError validateLoginUser(String account, String password){
        Customer customer = customerService.findByAccount(account);
        if(Objects.isNull(customer)){
            return buildMessageError(TypeMessageEnum.ERROR_ACCOUNT_NOT_FOUND);
        }
        if(!customer.getPassword().equalsIgnoreCase(password)){
            return buildMessageError(TypeMessageEnum.ERROR_PASSWORD_NOT_MATCH);
        }
        return buildMessageSuccess();
    }


}
