package factory;

import factory.handler.EmailHandler;
import model.Email;
import model.enums.EmailProviderType;
import reader.YAMLConfigReader;
import util.EmailUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmailHandlerFactoryImpl implements EmailHandlerFactory {

    private final YAMLConfigReader yamlConfigReader;

    public EmailHandlerFactoryImpl() {
        this.yamlConfigReader = YAMLConfigReader.getInstance();
    }

    public EmailHandlerFactoryImpl(final String yamlFilePath) {
        this.yamlConfigReader = YAMLConfigReader.getInstance(yamlFilePath);
    }

    /**
     * 1. Find the email-provider.
     * 2. Fetch the list of filter-handlers.
     * 3. Filter the email object into the ordered list.
     * @param email {@link Email} object.
     */
    @Override
    public void normalizeEmail(final Email email) {
        final EmailProviderType emailProviderType = EmailUtils.getEmailProviderType(email.getEmailProvider());
        final List<EmailHandler> emailHandlers = getEmailHandlerForEmailProviderType(emailProviderType);

        emailHandlers.forEach(emailHandler -> emailHandler.handle(email));
    }

    private List<EmailHandler> getEmailHandlerForEmailProviderType(final EmailProviderType emailProviderType) {
        Map<EmailProviderType, List<EmailHandler>> emailProviderTypeToHandlerListMap =
                yamlConfigReader.getEmailConfiguration().getEmailProviderTypeListMap();

        if (emailProviderTypeToHandlerListMap == null) {
            emailProviderTypeToHandlerListMap = new HashMap<>();
        }

        List<EmailHandler> emailHandlers = emailProviderTypeToHandlerListMap.get(emailProviderType);
        if (emailHandlers == null || emailHandlers.isEmpty()) {
            emailHandlers = emailProviderTypeToHandlerListMap.get(EmailProviderType.OTHER);
        }

        return emailHandlers;
    }
}
