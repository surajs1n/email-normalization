package util;

import exception.EmailNormalizationException;
import exception.EmailNormalizationExceptionType;

public final class EmailValidator {
    private EmailValidator() {

    }

    public static void validateEmail(String inputEmail) throws EmailNormalizationException {
        if (inputEmail != null) {
            inputEmail = inputEmail.trim();
        }

        if (EmailUtils.isNullOrEmpty(inputEmail)) {
            throw new EmailNormalizationException(
                    EmailNormalizationExceptionType.EMPTY_OR_NULL_EMAIL,
                    "[EmailNormalizer] Email is either null or empty"
            );
        }

        if (inputEmail.contains(" ")) {
            throw new EmailNormalizationException(
                    EmailNormalizationExceptionType.INVALID_EMAIL,
                    "[EmailNormalizer] Email contains space in between"
            );
        }

        final String [] splitEmails = inputEmail.split("@");
        if (splitEmails.length != 2) {
            throw new EmailNormalizationException(
                    EmailNormalizationExceptionType.INVALID_EMAIL,
                    "[EmailNormalizer] Email is invalid"
            );
        }

        if (EmailUtils.isNullOrEmpty(splitEmails[0])) {
            throw new EmailNormalizationException(
                    EmailNormalizationExceptionType.INVALID_EMAIL,
                    "[EmailNormalizer] Email Prefix is invalid"
            );
        }

        if (EmailUtils.isNullOrEmpty(splitEmails[1])) {
            throw new EmailNormalizationException(
                    EmailNormalizationExceptionType.INVALID_EMAIL,
                    "[EmailNormalizer] Email Suffix is invalid"
            );
        }

        final String [] splitEmailSuffix = splitEmails[1].split("\\.");
        if (splitEmailSuffix.length < 2) {
            throw new EmailNormalizationException(
                    EmailNormalizationExceptionType.INVALID_EMAIL,
                    "[EmailNormalizer] Email Suffix is invalid"
            );
        }
    }
}
