package services.impl;

import constants.CharacterConstant;
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

import static models.enums.StepEnum.*;
import static services.AdapterService.reader;

public class MenuServiceImpl extends MessageValidatorAdapter implements MenuService {
    @Override
    public StringBuilder getListMenu() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<< Welcome ").append(AdapterService.getInstanceCustomerService().getCurrentCustomerLogin().getName()).append(" come back >>").append(CharacterConstant.COMMAND_NEXT_LINE);
        stringBuilder.append("<< You Current Info Account >>").append(CharacterConstant.COMMAND_NEXT_LINE);
        stringBuilder.append(AdapterService.getInstanceCustomerService().getCurrentCustomerLogin().toString()).append(CharacterConstant.COMMAND_NEXT_LINE).append(CharacterConstant.COMMAND_NEXT_LINE);
        stringBuilder.append(PropertiesUtil.getProperty(TypeMessageEnum.QUESTION_1))
                .append(CharacterConstant.COMMAND_NEXT_LINE)
                .append(PropertiesUtil.getProperty(TypeMessageEnum.QUESTION_2))
                .append(CharacterConstant.COMMAND_NEXT_LINE)
                .append(PropertiesUtil.getProperty(TypeMessageEnum.QUESTION_3))
                .append(CharacterConstant.COMMAND_NEXT_LINE)
                .append(PropertiesUtil.getProperty(TypeMessageEnum.QUESTION_4))
                .append(CharacterConstant.COMMAND_NEXT_LINE)
                .append(PropertiesUtil.getProperty(TypeMessageEnum.QUESTION_5))
                .append(CharacterConstant.COMMAND_NEXT_LINE)
                .append(PropertiesUtil.getProperty(TypeMessageEnum.QUESTION_6))
                .append(CharacterConstant.COMMAND_NEXT_LINE)
                .append(PropertiesUtil.getProperty(TypeMessageEnum.QUESTION_7))
                .append(CharacterConstant.COMMAND_NEXT_LINE)
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
        String input = CharacterConstant.EMPTY_STRING;
        StepEnum step = STEP_FIRST;
        QuestionEnum questionEnum = null;
        do {
            print(CharacterConstant.SPACE_MENU);
            switch (step){
                case STEP_FIRST:
                    step = processFirstStep();
                    break;
                case STEP_PROCESS_LOGIN:
                    step = processLogin();
                    continue;
            }
            if(step == STEP_PROCESS_MENU_WHEN_HAD_LOGIN){
                print(getListMenu().append(CharacterConstant.COMMAND_NEXT_LINE)
                        .append("Please Choose menu:")
                        .toString());
                input = reader.readLine().trim();
                MessageError messageError = MenuValidator.validateListMenuWhenHadLogin(input);
                if (messageError.getStatusErrorEnum().equals(StatusErrorEnum.BAD_REQUEST)) {
                    print(messageError.getMessageError());
                    continue;
                }
                questionEnum = getQuestionEnum(input);
                AdapterService.getInstanceQuestionService().processQuestion(questionEnum);
            }
        } while (!input.equalsIgnoreCase(CharacterConstant.COMMAND_EXIT.toString()));
        print(CharacterConstant.SPACE_EXIT);
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
