package normalize;

import exception.EmailNormalizationException;
import exception.EmailNormalizationExceptionType;
import factory.EmailHandlerFactory;
import factory.EmailHandlerFactoryImpl;
import model.Email;
import org.apache.commons.validator.routines.EmailValidator;

public class EmailNormalizer {

    private static final EmailHandlerFactory emailHandlerFactory = new EmailHandlerFactoryImpl();

    public static String normalize(final String inputEmail) throws EmailNormalizationException {
        boolean isEmailValid = EmailValidator.getInstance().isValid(inputEmail);
        if (!isEmailValid) {
            throw new EmailNormalizationException(
                    EmailNormalizationExceptionType.INVALID_EMAIL,
                    "[EmailNormalizer] Email is Invalid"
            );
        }

        final String [] splitEmails = inputEmail.trim().split("@");
        final String [] splitEmailSuffix = splitEmails[1].split("\\.", 2);
        final Email email = Email.builder()
                .emailPrefix(splitEmails[0])
                .emailProvider(splitEmailSuffix[0])
                .topLevelDomain(splitEmailSuffix[1])
                .build();

        emailHandlerFactory.normalizeEmail(email);

        final String normalizedEmailId =
                String.format("%s@%s.%s", email.getEmailPrefix(), email.getEmailProvider(), email.getTopLevelDomain());

        return normalizedEmailId;
    }
}
