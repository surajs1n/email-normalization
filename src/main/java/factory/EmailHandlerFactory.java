package factory;

import model.Email;

public interface EmailHandlerFactory {

    public void normalizeEmail(final Email email);
}
