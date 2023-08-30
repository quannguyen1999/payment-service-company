package models;


import models.enums.StatusErrorEnum;

public class MessageError {

    private String messageError;

    private StatusErrorEnum statusErrorEnum;

    public static MessageError.MessageErrorBuilder builder() {
        return new MessageError.MessageErrorBuilder();
    }


    public MessageError(String messageError, StatusErrorEnum statusErrorEnum) {
        this.messageError = messageError;
        this.statusErrorEnum = statusErrorEnum;
    }

    public MessageError() {
    }

    public String getMessageError() {
        return messageError;
    }

    public void setMessageError(String messageError) {
        this.messageError = messageError;
    }

    public StatusErrorEnum getStatusErrorEnum() {
        return statusErrorEnum;
    }

    public void setStatusErrorEnum(StatusErrorEnum statusErrorEnum) {
        this.statusErrorEnum = statusErrorEnum;
    }

    public static class MessageErrorBuilder {
        private String messageError;

        private StatusErrorEnum statusErrorEnum;

        MessageErrorBuilder() {
        }

        public MessageError.MessageErrorBuilder messageError(String messageError) {
            this.messageError = messageError;
            return this;
        }

        public MessageError.MessageErrorBuilder statusErrorEnum(StatusErrorEnum statusErrorEnum) {
            this.statusErrorEnum = statusErrorEnum;
            return this;
        }

        public MessageError build() {
            return new MessageError(messageError, statusErrorEnum);
        }

    }
}
