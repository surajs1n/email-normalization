package factory.handler;

import model.Email;

public class RemoveCharacterFromEmailHandler implements EmailHandler {

    private final String patternOfCharacterToBeRemove = "[.]";

    @Override
    public void handle(Email email) {
        email.setEmailPrefix(email.getEmailPrefix().replaceAll(patternOfCharacterToBeRemove, ""));
    }
}
