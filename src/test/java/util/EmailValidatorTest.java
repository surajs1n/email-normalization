package util;

import exception.EmailNormalizationException;
import exception.EmailNormalizationExceptionType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailValidatorTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void GIVEN_nullEmailId_WHEN_NormalizingEmail_THEN_ThrowException() {
        EmailNormalizationException exception = assertThrows(EmailNormalizationException.class, () -> EmailValidator.validateEmail(null));
        assertEquals(exception.getExceptionType(), EmailNormalizationExceptionType.EMPTY_OR_NULL_EMAIL);
    }

    @Test
    public void GIVEN_emptyEmailId_WHEN_NormalizingEmail_THEN_ThrowException() {
        EmailNormalizationException exception = assertThrows(EmailNormalizationException.class, () -> EmailValidator.validateEmail(""));
        assertEquals(exception.getExceptionType(), EmailNormalizationExceptionType.EMPTY_OR_NULL_EMAIL);
    }

    @Test
    public void GIVEN_EmailIdHasSpaceInBetween_WHEN_NormalizingEmail_THEN_ThrowException() {
        EmailNormalizationException exception = assertThrows(EmailNormalizationException.class, () -> EmailValidator.validateEmail(" suraj .sn@gmail.com"));
        assertEquals(exception.getExceptionType(), EmailNormalizationExceptionType.INVALID_EMAIL);
    }

    @Test
    public void GIVEN_EmailIdHasMoreSpecialSymbol_WHEN_NormalizingEmail_THEN_ThrowException() {
        EmailNormalizationException exception = assertThrows(EmailNormalizationException.class, () -> EmailValidator.validateEmail(" suraj@sn@gmail.com"));
        assertEquals(exception.getExceptionType(), EmailNormalizationExceptionType.INVALID_EMAIL);

        exception = assertThrows(EmailNormalizationException.class, () -> EmailValidator.validateEmail(" suraj"));
        assertEquals(exception.getExceptionType(), EmailNormalizationExceptionType.INVALID_EMAIL);
    }

    @Test
    public void GIVEN_EmailIdHasEitherEmptyPrefixOrSuffix_WHEN_NormalizingEmail_THEN_ThrowException() {
        EmailNormalizationException exception = assertThrows(EmailNormalizationException.class, () -> EmailValidator.validateEmail(" @gmail.com"));
        assertEquals(exception.getExceptionType(), EmailNormalizationExceptionType.INVALID_EMAIL);

        exception = assertThrows(EmailNormalizationException.class, () -> EmailValidator.validateEmail(" suraj@"));
        assertEquals(exception.getExceptionType(), EmailNormalizationExceptionType.INVALID_EMAIL);

        exception = assertThrows(EmailNormalizationException.class, () -> EmailValidator.validateEmail(" suraj@gmail"));
        assertEquals(exception.getExceptionType(), EmailNormalizationExceptionType.INVALID_EMAIL);
    }
}