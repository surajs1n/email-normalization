package factory.handler;

import model.Email;

public class ToLowerEmailHandler implements EmailHandler {

    @Override
    public void handle(Email email) {
        email.setEmailPrefix(email.getEmailPrefix().toLowerCase());
        email.setEmailProvider(email.getEmailProvider().toLowerCase());
        email.setTopLevelDomain(email.getTopLevelDomain().toLowerCase());
    }
}
