package factory.handler;

import model.Email;

/**
 * EmailHandler class responsible to covert all the possible characters to lower-case characters.
 *
 * E.g:-
 * Input Email :- Surajs1N@gmail.com
 * Output Email:- surajs1n@gmail.com
 */
public class ToLowerEmailHandler extends EmailHandler {

    public ToLowerEmailHandler() {
        super(EmailHandlerType.TO_LOWERCASE);
    }

    @Override
    public void handle(Email email) {
        email.setEmailPrefix(email.getEmailPrefix().toLowerCase());
        email.setEmailProvider(email.getEmailProvider().toLowerCase());
        email.setTopLevelDomain(email.getTopLevelDomain().toLowerCase());
    }
}
