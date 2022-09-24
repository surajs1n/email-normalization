package factory.handler;

import model.Email;

public class TrimFromEmailHandler implements EmailHandler {

    @Override
    public void handle(Email email) {
        String [] splitPrefix = email.getEmailPrefix().split("\\+");
        email.setEmailPrefix(splitPrefix[0]);
    }
}
