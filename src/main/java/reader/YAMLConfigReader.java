package reader;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import config.EmailConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Central Config Reader to initialize EmailConfiguration class from corresponding YAML file.
 * It by default reads from src/main/resources/config/EmailConfigurations.yaml file and also has extensibility to
 * read from user inserted file path.
 */
public class YAMLConfigReader {

    private final ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
    private final String yamlFilePath;

    private final boolean isCustomConfigUsed;
    private static EmailConfiguration EMAIL_CONFIGURATION = null;

    private static EmailConfiguration CUSTOM_EMAIL_CONFIGURATION = null;
    private static YAMLConfigReader YAML_CONFIG_READER_INSTANCE  = null;
    private static YAMLConfigReader YAML_CONFIG_READER_CUSTOM_INSTANCE = null;

    private final String DEFAULT_PATH = "src/main/resources/config/EmailConfigurations.yaml";

    /**
     * Get the defaultInstance of {@link YAMLConfigReader} based on default yamlFilePath.
     * @return - {@link YAMLConfigReader} object.
     */
    public static YAMLConfigReader getInstance() {
        if (YAML_CONFIG_READER_INSTANCE  == null) {
            YAML_CONFIG_READER_INSTANCE = new YAMLConfigReader();
        }

        return YAML_CONFIG_READER_INSTANCE;
    }

    /**
     * Get the customInstance of {@link YAMLConfigReader} based on custom yamlFilePath.
     * @param yamlFilePath - customFilePath.
     * @return - {@link YAMLConfigReader} object.
     */
    public static YAMLConfigReader getInstance(final String yamlFilePath) {
        if (YAML_CONFIG_READER_CUSTOM_INSTANCE == null) {
            YAML_CONFIG_READER_CUSTOM_INSTANCE = new YAMLConfigReader(yamlFilePath);
        }

        return YAML_CONFIG_READER_CUSTOM_INSTANCE;
    }

    private YAMLConfigReader() {
        this.yamlFilePath = this.DEFAULT_PATH;
        this.isCustomConfigUsed = false;
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private YAMLConfigReader(String yamlFilePath) {
        this.yamlFilePath = yamlFilePath;
        this.isCustomConfigUsed = true;
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * Core function to fetch and data from yamlFilePath and de-serialized into {@link EmailConfiguration} object.
     * @return - {@link EmailConfiguration} object.
     */
    public EmailConfiguration getEmailConfiguration() {
        if (this.isCustomConfigUsed) {
            if (CUSTOM_EMAIL_CONFIGURATION != null) {
                return CUSTOM_EMAIL_CONFIGURATION;
            }

            CUSTOM_EMAIL_CONFIGURATION = readFromFile(DEFAULT_PATH);
            EmailConfiguration emailConfiguration = readFromFile(yamlFilePath);
            CUSTOM_EMAIL_CONFIGURATION.getEmailProviderTypeListMap().putAll(emailConfiguration.getEmailProviderTypeListMap());

            return CUSTOM_EMAIL_CONFIGURATION;
        } else {
            if (EMAIL_CONFIGURATION != null) {
                return EMAIL_CONFIGURATION;
            }

            EMAIL_CONFIGURATION = readFromFile(DEFAULT_PATH);
            return EMAIL_CONFIGURATION;
        }
    }

    private EmailConfiguration readFromFile(final String yamlFilePath) {
        try {
            return objectMapper.readValue(new File(yamlFilePath), EmailConfiguration.class);
        } catch (IOException e) {
            System.err.println("Error de-serializing YAML info back in");
            throw new RuntimeException(e);
        }
    }
}
