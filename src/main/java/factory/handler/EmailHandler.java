package factory.handler;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import model.Email;

/**
 * Abstract class contains all the contract of the derived EmailHandler classes responsible for normalization.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "handlerType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ToLowerEmailHandler.class, name = "TO_LOWERCASE"),
        @JsonSubTypes.Type(value = RemoveCharacterFromEmailHandler.class, name = "REMOVE_CHARACTERS"),
        @JsonSubTypes.Type(value = TrimFromEmailHandler.class, name = "TRIM_SUFFIX")
})
public abstract class EmailHandler {

    private final EmailHandlerType handlerType;

    /**
     * EmailHandler super constructor
     * @param handlerType - {@link EmailHandlerType} enum.
     */
    public EmailHandler(EmailHandlerType handlerType) {
        this.handlerType = handlerType;
    }

    /**
     * Function to accept Email object and normalize it.
     * @param email - {@link Email} object.
     */
    abstract public void handle(final Email email);
}
