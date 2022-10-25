package factory.handler;

import lombok.Setter;
import model.Email;

/**
 * EmailHandler class responsible to trim the suffix of emailPrefix from {@link Email} object.
 *
 * E.g:-
 * Given :- trimRegexExpression = "\\+"
 * Input Email  :- surajS.1N+11@gmail.com
 * Output Email :- surajS1N@gmail.com
 */

@Setter
public class TrimFromEmailHandler extends EmailHandler {

    private String trimRegexExpression;
    public TrimFromEmailHandler() {
        super(EmailHandlerType.TRIM_SUFFIX);
    }

    public TrimFromEmailHandler(final String trimRegexExpression) {
        super(EmailHandlerType.TRIM_SUFFIX);
        this.trimRegexExpression = trimRegexExpression;
    }

    @Override
    public void handle(Email email) {
        String [] splitPrefix = email.getEmailPrefix().split(trimRegexExpression);
        email.setEmailPrefix(splitPrefix[0]);
    }
}
