package models.enums;

public enum FilePathDataEnum {
    PATH_DATA_CSV_CUSTOMER("data/customerData.csv"),
    PATH_DATA_CSV_BILL("data/billData.csv"),

    PATH_DATA_CSV_QUESTION_1("test/dataQuestion1.csv"),
    PATH_DATA_CSV_QUESTION_3("test/dataQuestion3.csv"),

    PATH_DATA_CSV_QUESTION_5("test/dataQuestion5.csv"),

    PATH_DATA_CSV_QUESTION_6("test/dataQuestion6.csv"),

    PATH_DATA_CSV_QUESTION_7("test/dataQuestion7.csv"),
    PATH_DATA_PROP_MESSAGE("other/message.properties");


    private final String path;

    FilePathDataEnum(final String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return path;
    }
}
