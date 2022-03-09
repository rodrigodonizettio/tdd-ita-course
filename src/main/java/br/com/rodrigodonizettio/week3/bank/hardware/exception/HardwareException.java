package br.com.rodrigodonizettio.week3.bank.hardware.exception;

public class HardwareException extends Exception {
    private static final String DEFAULT_MESSAGE = "Something went wrong during hardware operation";
    private final String failedOperation;

    public HardwareException(String failedOperation) {
        this.failedOperation = failedOperation;
    }

    @Override
    public String getMessage() {
        return String.format("%s. Operation: %s", DEFAULT_MESSAGE, failedOperation);
    }
}
