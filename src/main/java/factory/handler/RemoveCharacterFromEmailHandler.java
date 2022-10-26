package factory.handler;

import lombok.Builder;
import lombok.Setter;
import model.Email;

/**
 * EmailHandler class responsible to remove noisy list of characters from the input {@link Email} object.
 *
 * E.g:-
 * Given: patternOfCharacterToBeRemoved = [.]
 * Input email :- suraj.S.1.n@gmail.com
 * Output email :- surajS1n@gmail.com
 */

@Setter
public class RemoveCharacterFromEmailHandler extends EmailHandler {

    private String patternOfCharacterToBeRemoved;

    public RemoveCharacterFromEmailHandler() {
        super(EmailHandlerType.REMOVE_CHARACTERS);
    }

    public RemoveCharacterFromEmailHandler(final String patternOfCharacterToBeRemoved) {
        super(EmailHandlerType.REMOVE_CHARACTERS);
        this.patternOfCharacterToBeRemoved = patternOfCharacterToBeRemoved;
    }

    @Override
    public void handle(Email email) {
        email.setEmailPrefix(email.getEmailPrefix().replaceAll(patternOfCharacterToBeRemoved, ""));
    }
}
