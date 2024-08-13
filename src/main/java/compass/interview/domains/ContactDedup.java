package compass.interview.domains;

import compass.interview.models.Contact;
import compass.interview.models.Match;
import compass.interview.models.MatchAccuracy;

import java.util.ArrayList;
import java.util.List;

public class ContactDedup {
    /**
     * Calculates a similarity score between two contacts ({@code Contact})
     * and assign an accuracy level {@code MatchAccuracy} based on ranges of similarity.
     *
     * <p>The similarity score is based on how many fields match between the two contacts. The score is normalized
     * between 0 and 1, where 1 indicates a perfect match and 0 indicates no match.</p>
     *
     * @param c1 The first contact.
     * @param c2 The second contact.
     * @return A double value representing the similarity score.
     */
    public MatchAccuracy calculateAccuracy(Contact c1, Contact c2) {
        int score = 0;
        int amountCriteria = 5;

        if (c1.getFirstName().equalsIgnoreCase(c2.getFirstName())) score++;
        if (c1.getLastName().equalsIgnoreCase(c2.getLastName())) score++;
        if (c1.getEmail().equalsIgnoreCase(c2.getEmail())) score++;
        if (c1.getZipCode().equalsIgnoreCase(c2.getZipCode())) score++;
        if (c1.getAddress().equalsIgnoreCase(c2.getAddress())) score++;

        double similarityScore = (double) score / amountCriteria;

        // Determine accuracy based on the score using the MatchAccuracy enum
        MatchAccuracy accuracy;
        if (similarityScore <= 0) {
            accuracy = MatchAccuracy.NONE;
        } else if (similarityScore > 0.7) {
            accuracy = MatchAccuracy.HIGH;
        } else if (similarityScore > 0.4) {
            accuracy = MatchAccuracy.MEDIUM;
        } else {
            accuracy = MatchAccuracy.LOW;
        }
        return accuracy;
    }

    /**
     * Identifies duplicate contacts from a list of contacts.
     *
     * @param contacts A list of {@code Contact} objects to check for duplicates.
     * @return A list of {@code Match} objects representing duplicates.
     */
    public List<Match> findDuplicates(List<Contact> contacts) {
        List<Match> matches = new ArrayList<>();

        // Iterate through the list of contacts twice,
        // so it is possible to compare every two pairs of different contacts
        for (int i = 0; i < contacts.size(); i++) {
            for (int j = i + 1; j < contacts.size(); j++) {
                Contact contact1 = contacts.get(i);
                Contact contact2 = contacts.get(j);

                // Calculate a match accuracy based on similarity
                MatchAccuracy accuracy = calculateAccuracy(contact1, contact2);

                // For valid matches, stores the match in the resulting list
                if (accuracy != MatchAccuracy.NONE) {
                    matches.add(new Match(contact1.getContactId(), contact2.getContactId(), accuracy));
                }
            }
        }
        return matches;
    }
}
