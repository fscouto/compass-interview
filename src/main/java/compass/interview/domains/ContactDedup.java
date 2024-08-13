package compass.interview.domains;

import compass.interview.models.Contact;
import compass.interview.models.MatchAccuracy;

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
        } else if (similarityScore > 0.8) {
            accuracy = MatchAccuracy.HIGH;
        } else if (similarityScore > 0.5) {
            accuracy = MatchAccuracy.MEDIUM;
        } else {
            accuracy = MatchAccuracy.LOW;
        }
        return accuracy;
    }
}
