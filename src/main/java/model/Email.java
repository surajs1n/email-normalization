package model;

import exception.EmailNormalizationException;
import exception.EmailNormalizationExceptionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import util.EmailUtils;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Email {
    private String emailPrefix;
    private String emailProvider;
    private String topLevelDomain;
}
