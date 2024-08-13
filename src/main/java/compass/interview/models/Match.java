package compass.interview.models;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * The {@code Match} class represents a potential match between two contacts.
 * It contains information about the source contact, the matched contact, and the accuracy of the match.
 *
 * <p>This class uses Lombok annotations to automatically generate boilerplate code such as getters,
 * setters, constructors (with all the arguments), and `toString`, `equals`, and `hashCode` methods.</p>
 */
@Data
@AllArgsConstructor
public class Match {
    /**
     * The ID of the source contact.
     */
    private int sourceContactId;

    /**
     * The ID of the matched contact.
     */
    private int matchedContactId;

    /**
     * The accuracy level of the match (e.g., "High", "Medium", "Low").
     */
    private MatchAccuracy accuracy;
}
