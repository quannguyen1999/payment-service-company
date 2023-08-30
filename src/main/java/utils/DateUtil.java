package utils;

import constants.CharacterConstant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class DateUtil {
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public static Date convertStringToDate(String value){
        try {
            return simpleDateFormat.parse(value);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String convertDateToString(Date value){
        return Objects.isNull(value) ? CharacterConstant.EMPTY_STRING : simpleDateFormat.format(value);
    }
}
