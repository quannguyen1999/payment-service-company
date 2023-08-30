package services.impl;

import constants.CommonCharacterConstant;
import models.Customer;
import models.MessageError;
import models.enums.QuestionEnum;
import models.enums.StatusErrorEnum;
import models.enums.StepEnum;
import models.enums.TypeMessageEnum;
import services.AdapterService;
import services.MenuService;
import utils.PropertiesUtil;
import validators.CustomerValidator;
import validators.MenuValidator;
import validators.MessageValidatorAdapter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static models.enums.StepEnum.STEP_FIRST;
import static models.enums.StepEnum.STEP_PROCESS_LOGIN;
import static models.enums.StepEnum.STEP_PROCESS_MENU_WHEN_HAD_LOGIN;
import static services.AdapterService.reader;

public class MenuServiceImpl extends MessageValidatorAdapter implements MenuService {
    @Override
    public StringBuilder getListMenu() {
       StringBuilder stringBuilder = new StringBuilder();
       stringBuilder.append("<< Welcome ").append(AdapterService.getInstanceCustomerService().getCurrentCustomerLogin().getName()).append(" come back >>").append(CommonCharacterConstant.COMMAND_NEXT_LINE);
       stringBuilder.append("<< You Current Info Account >>").append(CommonCharacterConstant.COMMAND_NEXT_LINE);
       stringBuilder.append(AdapterService.getInstanceCustomerService().getCurrentCustomerLogin().toString()).append(CommonCharacterConstant.COMMAND_NEXT_LINE).append(CommonCharacterConstant.COMMAND_NEXT_LINE);
       stringBuilder.append(PropertiesUtil.getProperty(TypeMessageEnum.QUESTION_1))
               .append(CommonCharacterConstant.COMMAND_NEXT_LINE)
               .append(PropertiesUtil.getProperty(TypeMessageEnum.QUESTION_2))
               .append(CommonCharacterConstant.COMMAND_NEXT_LINE)
               .append(PropertiesUtil.getProperty(TypeMessageEnum.QUESTION_3))
               .append(CommonCharacterConstant.COMMAND_NEXT_LINE)
               .append(PropertiesUtil.getProperty(TypeMessageEnum.QUESTION_4))
               .append(CommonCharacterConstant.COMMAND_NEXT_LINE)
               .append(PropertiesUtil.getProperty(TypeMessageEnum.QUESTION_5))
               .append(CommonCharacterConstant.COMMAND_NEXT_LINE)
               .append(PropertiesUtil.getProperty(TypeMessageEnum.QUESTION_6))
               .append(CommonCharacterConstant.COMMAND_NEXT_LINE)
               .append(PropertiesUtil.getProperty(TypeMessageEnum.QUESTION_7))
               .append(CommonCharacterConstant.COMMAND_NEXT_LINE)
               .append(PropertiesUtil.getProperty(TypeMessageEnum.QUESTION_EXIT));
       return stringBuilder;
    }

    @Override
    public StepEnum processLogin() throws IOException {
        //Handler Input
        String account;
        String password;
        PropertiesUtil.getPropertyAndPrint(TypeMessageEnum.MESSAGE_LOGIN);
        PropertiesUtil.getPropertyAndPrint(TypeMessageEnum.MESSAGE_ACCOUNT);
        account = reader.readLine();
        PropertiesUtil.getPropertyAndPrint(TypeMessageEnum.MESSAGE_PASSWORD);
        password = reader.readLine();

        //Validate login account
        MessageError messageError =  CustomerValidator.validateLoginUser(account, password);
        if(messageError.getStatusErrorEnum().equals(StatusErrorEnum.BAD_REQUEST)){
            print(messageError.getMessageError());
            return STEP_PROCESS_LOGIN;
        }

        //set Data
        Customer customer = AdapterService.getInstanceCustomerService().findByAccount(account);
        AdapterService.getInstanceCustomerService().setCurrentCustomerLogin(customer);
        return STEP_PROCESS_MENU_WHEN_HAD_LOGIN;
    }

    @Override
    public void startMenu() throws IOException {
        String input = CommonCharacterConstant.EMPTY_STRING;
        StepEnum step = STEP_FIRST;
        QuestionEnum questionEnum = null;
        do {
            print(CommonCharacterConstant.SPACE_MENU);
            switch (step){
                case STEP_FIRST:
                    step = processFirstStep();
                    break;
                case STEP_PROCESS_LOGIN:
                    step = processLogin();
                    continue;
            }
            if(step == STEP_PROCESS_MENU_WHEN_HAD_LOGIN){
                print(getListMenu().append(CommonCharacterConstant.COMMAND_NEXT_LINE)
                        .append("Please Choose menu:")
                        .toString());
                input = reader.readLine().trim();
                MessageError messageError =  MenuValidator.validateListMenuWhenHadLogin(input);
                if(messageError.getStatusErrorEnum().equals(StatusErrorEnum.BAD_REQUEST)){
                    print(messageError.getMessageError());
                    continue;
                }
                questionEnum = getQuestionEnum(input);
                AdapterService.getInstanceQuestionService().processQuestion(questionEnum);
            }
        } while (!input.equalsIgnoreCase(CommonCharacterConstant.COMMAND_EXIT.toString()));
        print(CommonCharacterConstant.SPACE_EXIT);
    }

    private StepEnum processFirstStep() throws IOException {
        //Get List Menu
        buildMenu(Arrays.asList(TypeMessageEnum.MESSAGE_CHOOSE_MENU,
                TypeMessageEnum.QUESTION_0,
                TypeMessageEnum.QUESTION_EXIT
        ));

        //Input Choose Menu
        String input = reader.readLine().trim();
        MessageError messageError =  MenuValidator.validateFirstStep(input);
        if(messageError.getStatusErrorEnum().equals(StatusErrorEnum.BAD_REQUEST)){
            print(messageError.getMessageError());
            return STEP_FIRST;
        }else{
            return STEP_PROCESS_LOGIN;
        }
    }

    private void buildMenu(List<TypeMessageEnum> typeMessageEnums){
        typeMessageEnums.forEach(PropertiesUtil::getPropertyAndPrint);
    }

    private QuestionEnum getQuestionEnum(String input){
        return Arrays.stream(QuestionEnum.values()).filter(t-> t.getInput().equalsIgnoreCase(input)).findFirst().orElse(null);
    }


}
