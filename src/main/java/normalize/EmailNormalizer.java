package normalize;

import exception.EmailNormalizationException;
import exception.EmailNormalizationExceptionType;
import factory.EmailHandlerFactory;
import factory.EmailHandlerFactoryImpl;
import model.Email;
import org.apache.commons.validator.routines.EmailValidator;

/**
 * It is the entry-point of the library.
 */
public class EmailNormalizer {

    private final EmailHandlerFactory emailHandlerFactory;
    private static EmailNormalizer EMAIL_NORMALIZER_INSTANCE = null;
    private static EmailNormalizer EMAIL_NORMALIZER_CUSTOM_INSTANCE = null;

    public static EmailNormalizer getInstance() {
        if (EMAIL_NORMALIZER_INSTANCE == null) {
            EMAIL_NORMALIZER_INSTANCE = new EmailNormalizer();
        }

        return EMAIL_NORMALIZER_INSTANCE;
    }

    public static EmailNormalizer getInstance(final String yamlFilePath) {
        if (EMAIL_NORMALIZER_CUSTOM_INSTANCE == null) {
            EMAIL_NORMALIZER_CUSTOM_INSTANCE = new EmailNormalizer(yamlFilePath);
        }

        return EMAIL_NORMALIZER_CUSTOM_INSTANCE;
    }

    private EmailNormalizer() {
        this.emailHandlerFactory = new EmailHandlerFactoryImpl();
    }

    private EmailNormalizer(final String yamlFilePath) {
        this.emailHandlerFactory = new EmailHandlerFactoryImpl(yamlFilePath);
    }

    /**
     * Function does all the validation and normalization activites.
     * @param inputEmail - input emailId.
     * @return normalization EmailId.
     * @throws EmailNormalizationException - throws exception in case something goes wrong.
     */
    public String normalize(final String inputEmail) throws EmailNormalizationException {
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

        return String.format("%s@%s.%s", email.getEmailPrefix(), email.getEmailProvider(), email.getTopLevelDomain());
    }
}
