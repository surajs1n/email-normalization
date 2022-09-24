package exception;

public class EmailNormalizationException extends Exception {
    private final EmailNormalizationExceptionType exceptionType;

    public EmailNormalizationException(final EmailNormalizationExceptionType exceptionType,
                                       final String errorMessage) {
        super(errorMessage);
        this.exceptionType = exceptionType;
    }

    public EmailNormalizationException(final EmailNormalizationExceptionType exceptionType,
                                       final String errorMessage, Throwable cause) {
        super(errorMessage, cause);
        this.exceptionType = exceptionType;
    }

    public EmailNormalizationExceptionType getExceptionType() {
        return exceptionType;
    }
}
