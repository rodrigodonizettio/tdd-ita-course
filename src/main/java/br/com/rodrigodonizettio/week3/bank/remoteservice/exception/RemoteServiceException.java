package br.com.rodrigodonizettio.week3.bank.remoteservice.exception;

public class RemoteServiceException extends Exception {
    private static final String DEFAULT_MESSAGE = "Something went wrong during Remote Service operation";
    private final String failedOperation;

    public RemoteServiceException(String failedOperation) {
        this.failedOperation = failedOperation;
    }

    @Override
    public String getMessage() {
        return String.format("%s. Operation: %s", DEFAULT_MESSAGE, failedOperation);
    }
}
