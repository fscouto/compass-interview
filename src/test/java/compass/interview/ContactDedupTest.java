package compass.interview;

import compass.interview.domains.ContactDedup;
import compass.interview.models.Contact;

import compass.interview.models.MatchAccuracy;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * The {@code ContactDedupTest} class is used to test the contact deduplication logic.
 */
public class ContactDedupTest {

    private ContactDedup dedup;

    /**
     * Initializes the classes uses before each test.
     */
    @Before
    public void setUp() {
        dedup = new ContactDedup();
    }

    /**
     * Tests the accuracy calculation method for different cases.
     */
    @Test
    public void testCalculateSimilarity() {
        Contact contact1 = new Contact(1001, "John", "Doe", "john.doe@example.com", "12345", "123 Elm St.");
        Contact contact2 = new Contact(1002, "John", "Doe", "john.doe@example.com", "12345", "123 Elm St.");
        Contact contact3 = new Contact(1003, "Jane", "Doe", "jane.doe@example.com", "12345", "123 Elm St.");
        Contact contact4 = new Contact(1003, "Arthur", "Strange", "arthur.strange@another.com.br", "87654", "987 Other St.");

        // Test identical contacts
        MatchAccuracy accuracy1 = dedup.calculateAccuracy(contact1, contact2);
        assertEquals("Identical contacts should have a similarity of 1.0, then HIGH accuracy", MatchAccuracy.HIGH, accuracy1);

        // Test different contacts with some matching field
        MatchAccuracy accuracy2 = dedup.calculateAccuracy(contact1, contact3);
        assertEquals("Different contacts with zipCode and address matching should have a MEDIUM accuracy",
                MatchAccuracy.MEDIUM, accuracy2);

        // Test totally different contacts with no matching fields
        MatchAccuracy accuracy3 = dedup.calculateAccuracy(contact3, contact4);
        assertEquals("Totally different contacts should have accuracy as NONE",
                MatchAccuracy.NONE, accuracy3);
    }
}
