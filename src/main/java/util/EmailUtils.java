package util;

import factory.handler.EmailHandler;
import factory.handler.RemoveCharacterFromEmailHandler;
import factory.handler.ToLowerEmailHandler;
import factory.handler.TrimFromEmailHandler;
import model.enums.EmailProviderType;

import java.util.*;

public final class EmailUtils {

    private EmailUtils() {

    }

    private static final ToLowerEmailHandler toLowerEmailHandler = new ToLowerEmailHandler();
    private static final RemoveCharacterFromEmailHandler removeCharacterFromEmailHandler = new RemoveCharacterFromEmailHandler();
    private static final TrimFromEmailHandler trimFromEmailHandler = new TrimFromEmailHandler();

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

    private static final Map<EmailProviderType, List<EmailHandler>>  emailProviderTypeToHandlerListMap = new HashMap<>() {{
       put(EmailProviderType.GMAIL, new ArrayList<>(Arrays.asList(toLowerEmailHandler, removeCharacterFromEmailHandler, trimFromEmailHandler)));
       put(EmailProviderType.OUTLOOK, new ArrayList<>(Arrays.asList(toLowerEmailHandler)));
       put(EmailProviderType.AOL, new ArrayList<>(Arrays.asList(toLowerEmailHandler)));
       put(EmailProviderType.YAHOO, new ArrayList<>(Arrays.asList(toLowerEmailHandler)));
       put(EmailProviderType.ICLOUD, new ArrayList<>(Arrays.asList(toLowerEmailHandler)));
       put(EmailProviderType.ZOHO, new ArrayList<>(Arrays.asList(toLowerEmailHandler)));
       put(EmailProviderType.YANDEX, new ArrayList<>(Arrays.asList(toLowerEmailHandler)));
       put(EmailProviderType.TUTANOTA, new ArrayList<>(Arrays.asList(toLowerEmailHandler)));
       put(EmailProviderType.OTHER, new ArrayList<>(Arrays.asList(toLowerEmailHandler)));
    }};


    public static EmailProviderType getEmailProviderType(final String emailProvider) {
        EmailProviderType type = nameToEmailProviderType.get(emailProvider.toLowerCase());

        if (type == null) {
            type = EmailProviderType.OTHER;
        }

        return  type;
    }

    public static List<EmailHandler> getEmailHandlerForEmailProviderType(final EmailProviderType emailProviderType) {
        List<EmailHandler> emailHandlers = emailProviderTypeToHandlerListMap.get(emailProviderType);

        if (emailHandlers == null || emailHandlers.isEmpty()) {
            emailHandlers = emailProviderTypeToHandlerListMap.get(EmailProviderType.OTHER);
        }

        return emailHandlers;
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.equals("");
    }
}
