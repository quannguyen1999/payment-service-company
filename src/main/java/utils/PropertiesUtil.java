package utils;

import models.enums.FilePathDataEnum;
import models.enums.TypeMessageEnum;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;
public class PropertiesUtil {

    private static Properties properties = null;

    public static Properties getProperties(FilePathDataEnum filePathDataEnum) {
        if (filePathDataEnum == FilePathDataEnum.PATH_DATA_PROP_MESSAGE) {
            return getInstance(filePathDataEnum);
        }
        throw new IllegalArgumentException("File Path Properties Not Found");
    }

    private static synchronized Properties getInstance(FilePathDataEnum filePathDataEnum){
        try (InputStream input = new FileInputStream(Thread.currentThread().getContextClassLoader().getResource(filePathDataEnum.getPath()).getFile())) {
            if(Objects.isNull(properties)){
                properties = new Properties();
                properties.load(input);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }

    public static String getProperty(TypeMessageEnum key){
        return properties.getProperty(key.toString());
    }

    public static void getPropertyAndPrint(TypeMessageEnum key){
        System.out.println("> " + properties.getProperty(key.toString()));
    }
}
