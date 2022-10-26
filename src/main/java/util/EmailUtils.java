package util;

import factory.handler.EmailHandler;
import factory.handler.RemoveCharacterFromEmailHandler;
import factory.handler.ToLowerEmailHandler;
import factory.handler.TrimFromEmailHandler;
import model.enums.EmailProviderType;

import java.util.*;

/**
 * Util class of Email-Normalization library.
 */
public final class EmailUtils {

    /**
     * Private default constructor to avoid creating it object.
     */
    private EmailUtils() {

    }

    private static final Map<String, EmailProviderType> nameToEmailProviderType = new HashMap<>() {{
        put("gmail", EmailProviderType.GMAIL);
        put("googlemail", EmailProviderType.GMAIL);
        put("outlook", EmailProviderType.OUTLOOK);
        put("aol", EmailProviderType.AOL);
        put("yahoo", EmailProviderType.YAHOO);
        put("yahoomail", EmailProviderType.YAHOO);
        put("icloud", EmailProviderType.ICLOUD);
        put("zoho", EmailProviderType.ZOHO);
        put("yandex", EmailProviderType.YANDEX);
        put("tutanota", EmailProviderType.TUTANOTA);
    }};

    /**
     * To fetch {@link  EmailProviderType} enum based on emailProvider string.
     * @param emailProvider - input string value.
     * @return - {@link  EmailProviderType} enum.
     */
    public static EmailProviderType getEmailProviderType(final String emailProvider) {
        EmailProviderType type = nameToEmailProviderType.get(emailProvider.toLowerCase());

        if (type == null) {
            type = EmailProviderType.OTHER;
        }

        return  type;
    }

    /**
     * Generic function to test non-null and not-empty string
     * @param str - input string.
     * @return - true/false based on non-null and not-empty string.
     */
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.equals("");
    }
}