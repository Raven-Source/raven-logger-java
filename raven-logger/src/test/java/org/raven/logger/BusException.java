package org.raven.logger;

public class BusException extends Exception implements Coder {

    private String code;

    public BusException() {
        super();
    }

    public BusException(String message) {
        super(message);
    }

    public BusException(String message, String code) {
        this(message);
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }
}
