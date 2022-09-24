package normalize;

import exception.EmailNormalizationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailNormalizerTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    ///////// TEST GMAIL IDs ////////
    @Test
    public void GIVEN_gmailIdWithDot_WHEN_Normalizing_THEN_NormalizeIt() throws EmailNormalizationException {
        String normalizedEmail = EmailNormalizer.normalize("Suraj.Sn@gmail.com");
        assertEquals( "surajsn@gmail.com", normalizedEmail);
    }

    @Test
    public void GIVEN_gmailIdWithDotAndPlus_WHEN_Normalizing_THEN_NormalizeIt() throws EmailNormalizationException {
        String normalizedEmail = EmailNormalizer.normalize("Suraj.Sn+1232+3@gmail.com");
        assertEquals( "surajsn@gmail.com", normalizedEmail);
    }

    @Test
    public void GIVEN_gmailIdWithDotAndNumber_WHEN_Normalizing_THEN_NormalizeIt() throws EmailNormalizationException {
        String normalizedEmail = EmailNormalizer.normalize("Suraj.Sn12.3.23@gmail.com");
        assertEquals( "surajsn12323@gmail.com", normalizedEmail);
    }

    @Test
    public void GIVEN_gmailIdWithDotAndNumberAndRegion_WHEN_Normalizing_THEN_NormalizeIt() throws EmailNormalizationException {
        String normalizedEmail = EmailNormalizer.normalize("Suraj.Sn12.3.23@gmail.co.in");
        assertEquals( "surajsn12323@gmail.co.in", normalizedEmail);
    }

    ///// TEST GOOGLE-MAIL IDs  ///////
    @Test
    public void GIVEN_googleMailIdWithDot_WHEN_Normalizing_THEN_NormalizeIt() throws EmailNormalizationException {
        String normalizedEmail = EmailNormalizer.normalize("Suraj.Sn@googlemail.com");
        assertEquals( "surajsn@googlemail.com", normalizedEmail);
    }

    @Test
    public void GIVEN_googleMailIdWithDotAndPlus_WHEN_Normalizing_THEN_NormalizeIt() throws EmailNormalizationException {
        String normalizedEmail = EmailNormalizer.normalize("Suraj.Sn+1232+3@googlemail.com");
        assertEquals( "surajsn@googlemail.com", normalizedEmail);
    }

    @Test
    public void GIVEN_googleMailIdWithDotAndNumber_WHEN_Normalizing_THEN_NormalizeIt() throws EmailNormalizationException {
        String normalizedEmail = EmailNormalizer.normalize("Suraj.Sn12.3.23@googlemail.com");
        assertEquals( "surajsn12323@googlemail.com", normalizedEmail);
    }

    ///// TEST OUTLOOK IDs  ///////
    @Test
    public void GIVEN_outlookId_WHEN_Normalizing_THEN_NormalizeIt() throws EmailNormalizationException {
        String normalizedEmail = EmailNormalizer.normalize("SurajSn123@OUTLOOK.com");
        assertEquals( "surajsn123@outlook.com", normalizedEmail);
    }

    ///// TEST AOL IDs  ///////
    @Test
    public void GIVEN_aolId_WHEN_Normalizing_THEN_NormalizeIt() throws EmailNormalizationException {
        String normalizedEmail = EmailNormalizer.normalize("SurajSn123@Aol.com");
        assertEquals( "surajsn123@aol.com", normalizedEmail);
    }

    ///// TEST YAHOO IDs  ///////
    @Test
    public void GIVEN_yahooId_WHEN_Normalizing_THEN_NormalizeIt() throws EmailNormalizationException {
        String normalizedEmail = EmailNormalizer.normalize("SurajSn123@YAHoo.com");
        assertEquals( "surajsn123@yahoo.com", normalizedEmail);
    }

    ///// TEST YAHOO-MAIL IDs  ///////
    @Test
    public void GIVEN_yahooMailId_WHEN_Normalizing_THEN_NormalizeIt() throws EmailNormalizationException {
        String normalizedEmail = EmailNormalizer.normalize("SurajSn123@YahoomaIL.com");
        assertEquals( "surajsn123@yahoomail.com", normalizedEmail);
    }

    ///// TEST ICLOUD IDs  ///////
    @Test
    public void GIVEN_iCloudId_WHEN_Normalizing_THEN_NormalizeIt() throws EmailNormalizationException {
        String normalizedEmail = EmailNormalizer.normalize("SurajSn123@ICloud.com");
        assertEquals( "surajsn123@icloud.com", normalizedEmail);
    }

    ///// TEST ZOHO IDs  ///////
    @Test
    public void GIVEN_zohoId_WHEN_Normalizing_THEN_NormalizeIt() throws EmailNormalizationException {
        String normalizedEmail = EmailNormalizer.normalize("SurajSn123@zOHO.com");
        assertEquals( "surajsn123@zoho.com", normalizedEmail);
    }

    ///// TEST YANDEX IDs  ///////
    @Test
    public void GIVEN_yandexId_WHEN_Normalizing_THEN_NormalizeIt() throws EmailNormalizationException {
        String normalizedEmail = EmailNormalizer.normalize("SurajSn123@YandeX.com");
        assertEquals( "surajsn123@yandex.com", normalizedEmail);
    }

    ///// TEST TUTANOTA IDs  ///////
    @Test
    public void GIVEN_tutanotaId_WHEN_Normalizing_THEN_NormalizeIt() throws EmailNormalizationException {
        String normalizedEmail = EmailNormalizer.normalize("SurajSn123@tutaNOTa.com");
        assertEquals( "surajsn123@tutanota.com", normalizedEmail);
    }

    ///// TEST OTHER IDs  ///////
    @Test
    public void GIVEN_otherId_WHEN_Normalizing_THEN_NormalizeIt() throws EmailNormalizationException {
        String normalizedEmail = EmailNormalizer.normalize("SurajSn123@random.co.in");
        assertEquals( "surajsn123@random.co.in", normalizedEmail);
    }
}