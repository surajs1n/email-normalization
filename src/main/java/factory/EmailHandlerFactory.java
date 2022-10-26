package factory;

import model.Email;

/**
 * EmailHandler Factory Interface
 */
public interface EmailHandlerFactory {

    /**
     * Function responsible to normalize input Email object.
     * @param email - {@link Email} object.
     */
    public void normalizeEmail(final Email email);
}
