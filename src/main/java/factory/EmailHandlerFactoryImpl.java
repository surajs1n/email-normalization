package factory;

import factory.handler.EmailHandler;
import model.Email;
import model.enums.EmailProviderType;
import util.EmailUtils;

import java.util.List;

public class EmailHandlerFactoryImpl implements EmailHandlerFactory {

    /**
     * 1. Find the email-provider.
     * 2. Fetch the list of filter-handlers.
     * 3. Filter the email object into the ordered list.
     * @param email {@link Email} object.
     */
    @Override
    public void normalizeEmail(final Email email) {
        final EmailProviderType emailProviderType = EmailUtils.getEmailProviderType(email.getEmailProvider());
        final List<EmailHandler> emailHandlers = EmailUtils.getEmailHandlerForEmailProviderType(emailProviderType);

        emailHandlers.forEach(emailHandler -> emailHandler.handle(email));
    }
}
