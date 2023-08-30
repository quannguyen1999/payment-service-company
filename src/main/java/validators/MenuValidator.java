package validators;

import models.MessageError;
import models.enums.StepEnum;
import models.enums.TypeMessageEnum;

import java.util.Objects;

public class MenuValidator extends MessageValidatorAdapter {

    public static MessageError validateFirstStep(String menuId){
        if(!menuId.equalsIgnoreCase(StepEnum.STEP_FIRST.getInput()) && !menuId.equalsIgnoreCase(StepEnum.STEP_EXIT.getInput())){
            return  buildMessageError(TypeMessageEnum.ERROR_MENU_NOT_EXISTS);
        }
        return buildMessageSuccess();
    }

    public static MessageError validateListMenuWhenHadLogin(String menuId){
        if(Objects.isNull(menuId)){
            return buildMessageError(TypeMessageEnum.ERROR_MENU_NOT_EXISTS);
        }
        int idMenu = Integer.parseInt(menuId);
        if(1 > idMenu || idMenu > 9){
            return  buildMessageError(TypeMessageEnum.ERROR_MENU_NOT_EXISTS);
        }
        return buildMessageSuccess();
    }

    public static MessageError validateQuestion1(String menuId){
        if(Objects.isNull(menuId)){
            return buildMessageError(TypeMessageEnum.ERROR_MENU_NOT_EXISTS);
        }
        int idMenu = Integer.parseInt(menuId);
        if(1 != idMenu &&  idMenu != 9){
            return buildMessageError(TypeMessageEnum.ERROR_MENU_NOT_EXISTS);
        }
        return buildMessageSuccess();
    }
}
