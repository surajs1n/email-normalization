package model;

import exception.EmailNormalizationException;
import exception.EmailNormalizationExceptionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import util.EmailUtils;

/**
 * Email object. It contains following attributes:
 * 1. emailPrefix - string value which appears before '@'
 * 2. emailProvider - string value denoting provider name, that appears right after '@'
 * 3. topLevelDomain - string value appears right after emailProvider after the '.'
 *
 * E.g:-
 * For this input :- surajs1n@gmail.com
 * emailPrefix :- surajs1n
 * emailProvider :- gmail
 * topLevelDomain :- com
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Email {
    private String emailPrefix;
    private String emailProvider;
    private String topLevelDomain;
}
