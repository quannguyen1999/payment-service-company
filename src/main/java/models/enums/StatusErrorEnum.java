package models.enums;

public enum StatusErrorEnum {
    SUCCESS(200),
    INTERNAL_SERVER_ERROR(500),
    BAD_REQUEST(400);

    private final Integer code;

    StatusErrorEnum(final Integer code) {
        this.code = code;
    }

}
