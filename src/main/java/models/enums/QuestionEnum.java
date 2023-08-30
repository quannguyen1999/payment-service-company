package models.enums;

public enum QuestionEnum {
    QUESTION_0("0"),
    QUESTION_1("1"),
    QUESTION_2("2"),
    QUESTION_3("3"),
    QUESTION_4("4"),
    QUESTION_5("5"),
    QUESTION_6("6"),
    QUESTION_7("7"),
    QUESTION_EXIT("9");

    private final String input;

    QuestionEnum(final String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
    }
}
