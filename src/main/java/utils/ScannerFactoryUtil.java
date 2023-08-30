package utils;

import models.enums.FilePathDataEnum;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

//Get File Csv
public class ScannerFactoryUtil {
    public static Scanner getScanner(FilePathDataEnum filePathDataEnum) {
        switch (filePathDataEnum) {
            case PATH_DATA_CSV_BILL:
            case PATH_DATA_CSV_CUSTOMER:
            case PATH_DATA_CSV_QUESTION_1:
            case PATH_DATA_CSV_QUESTION_3:
            case PATH_DATA_CSV_QUESTION_5:
            case PATH_DATA_CSV_QUESTION_6:
            case PATH_DATA_CSV_QUESTION_7:
                return getInstance(filePathDataEnum);
            default:
                throw new IllegalArgumentException("File Path Csv Not Found");
        }
    }

    private static synchronized Scanner getInstance(FilePathDataEnum filePathDataEnum){
        Scanner scanner;
        try {
            scanner = new Scanner(new File(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(filePathDataEnum.getPath())).getFile()));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return scanner;
    }

}
