package compass.interview;

import compass.interview.domains.ContactDedup;
import compass.interview.models.Contact;
import compass.interview.models.Match;

import compass.interview.models.MatchAccuracy;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * Tests the method that identifies duplicate contacts.
     */
    @Test
    public void testFindPotentialDuplicates() {
        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact(1001, "C", "F", "mollis.lectus.pede@outlook.net", "", "449-6990 Tellus. Rd."));
        contacts.add(new Contact(1002, "C", "French", "mollis.lectus.pede@outlook.net", "39746", "449-6990 Tellus. Rd."));
        contacts.add(new Contact(1003, "Ciara", "F", "non.lacinia.at@zoho.ca", "39746", ""));

        List<Match> matches = dedup.findDuplicates(contacts);

        // Verify the size of the matches list
        assertEquals("There should be 3 potential matches", 3, matches.size());

        // Verify the first match
        Match match1 = matches.get(0);
        assertEquals(1001, match1.getSourceContactId());
        assertEquals(1002, match1.getMatchedContactId());
        assertEquals(MatchAccuracy.MEDIUM, match1.getAccuracy());

        // Verify the second match
        Match match2 = matches.get(1);
        assertEquals(1001, match2.getSourceContactId());
        assertEquals(1003, match2.getMatchedContactId());
        assertEquals(MatchAccuracy.LOW, match2.getAccuracy());

        // Verify the third match
        Match match3 = matches.get(2);
        assertEquals(1002, match3.getSourceContactId());
        assertEquals(1003, match3.getMatchedContactId());
        assertEquals(MatchAccuracy.LOW, match3.getAccuracy());
    }
}
