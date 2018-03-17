package com.recover.exception;

/**
 * @author sanyue
 */
public class MineException extends RuntimeException {
    private String errorMessage;

    public MineException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public MineException(String s, String errorMessage) {
        super(s);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
