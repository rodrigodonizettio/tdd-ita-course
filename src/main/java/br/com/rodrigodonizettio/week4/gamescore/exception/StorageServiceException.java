package br.com.rodrigodonizettio.week4.gamescore.exception;

public class StorageServiceException extends Exception {
    private static final String DEFAULT_MESSAGE = "Something went wrong during Storage Service operation";
    private final String failedOperation;

    public StorageServiceException(String failedOperation) {
        this.failedOperation = failedOperation;
    }

    @Override
    public String getMessage() {
        return String.format("%s. Operation: %s", DEFAULT_MESSAGE, failedOperation);
    }
}
