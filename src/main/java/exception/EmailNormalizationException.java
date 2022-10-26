package exception;

/**
 * EmailNormalization Exception class
 */
public class EmailNormalizationException extends Exception {
    private final EmailNormalizationExceptionType exceptionType;

    /**
     * Custom exception constructor, expects exception-type and custom-errorMessage.
     * @param exceptionType - {@link EmailNormalizationExceptionType} enum.
     * @param errorMessage - custom error message.
     */
    public EmailNormalizationException(final EmailNormalizationExceptionType exceptionType,
                                       final String errorMessage) {
        super(errorMessage);
        this.exceptionType = exceptionType;
    }

    /**
     * Custom exception constructor, expects exception-type and custom-errorMessage.
     * @param exceptionType - {@link EmailNormalizationExceptionType} enum.
     * @param errorMessage - custom error message.
     * @param cause - Throwable object.
     */
    public EmailNormalizationException(final EmailNormalizationExceptionType exceptionType,
                                       final String errorMessage, Throwable cause) {
        super(errorMessage, cause);
        this.exceptionType = exceptionType;
    }

    /**
     * Function to fetch exception-type.
     * @return - {@link EmailNormalizationException} object.
     */
    public EmailNormalizationExceptionType getExceptionType() {
        return exceptionType;
    }
}
