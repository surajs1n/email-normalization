package config;

import factory.handler.EmailHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.enums.EmailProviderType;

import java.util.List;
import java.util.Map;

/**
 * Central class to keep all the configurations related to email-normalization library.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailConfiguration {

    private Map<EmailProviderType, List<EmailHandler>> emailProviderTypeListMap;

}
