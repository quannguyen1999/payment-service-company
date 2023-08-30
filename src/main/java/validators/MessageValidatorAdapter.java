package validators;

import models.MessageError;
import models.enums.TypeMessageEnum;
import utils.PropertiesUtil;

import static models.enums.StatusErrorEnum.BAD_REQUEST;
import static models.enums.StatusErrorEnum.SUCCESS;

public class MessageValidatorAdapter {

    static MessageError buildMessageError(TypeMessageEnum typeMessageEnum){
        return MessageError.builder()
                .messageError(PropertiesUtil.getProperty(typeMessageEnum))
                .statusErrorEnum(BAD_REQUEST)
                .build();
    }

    static MessageError buildMessageSuccess(){
        return MessageError.builder()
                .messageError(PropertiesUtil.getProperty(TypeMessageEnum.MESSAGE_SUCCESS))
                .statusErrorEnum(SUCCESS)
                .build();
    }

    public static void print(String value){
        System.out.println(value);
    }

    public static void printError(MessageError messageError){
        System.out.println(">>Log: " + messageError.getMessageError());
    }



}
