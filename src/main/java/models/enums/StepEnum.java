package models.enums;

public enum StepEnum {
    STEP_FIRST("0"),
    STEP_PROCESS_LOGIN("1"),
    STEP_PROCESS_MENU_WHEN_HAD_LOGIN("2"),
    STEP_EXIT("3");

    private final String input;

    StepEnum(final String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
    }
}
