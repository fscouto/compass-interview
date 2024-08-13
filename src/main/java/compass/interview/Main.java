package compass.interview;

import compass.interview.domains.ContactDedup;
import compass.interview.models.Contact;
import compass.interview.models.Match;
import compass.interview.models.MatchAccuracy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The {@code Main} class is the entry point of the Contact Dedup application.
 * It reads a CSV file containing contact information, processes it to find duplicates,
 * and prints the results.
 */
public class Main {

    /**
     * The main method that runs the Contact Dedup application.
     *
     * @param args Command-line arguments, expects the CSV file path as the first argument.
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Could not read CSV file from first parameter");
            System.exit(1);
        }

        String csvFileName = args[0];

        // Load contacts from the CSV file
        List<Contact> contacts = loadContactsFromCsv(csvFileName);

        if (contacts.isEmpty()) {
            System.err.println("No contacts found.");
            System.exit(1);
        }

        // Instantiate the ContactDedup class
        ContactDedup deduplication = new ContactDedup();

        // Find duplicate contacts
        List<Match> matches = deduplication.findDuplicates(contacts);

        // Print out the matches
        for (Match match : matches) {
            System.out.println(match.toString());
        }
    }

    /**
     * Loads contacts from a CSV file.
     *
     * @param filePath The full path to the CSV file.
     * @return A list of {@code Contact} objects parsed from the CSV file.
     */
    private static List<Contact> loadContactsFromCsv(String filePath) {
        List<Contact> contacts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            // Ignores the header
            String line = reader.readLine();
            if (line != null) {
                // Read each line at a time
                while ((line = reader.readLine()) != null) {
                    // Split one row by comma
                    String[] values = line.split(",");
                    if (values.length >= 6) {
                        int contactId = Integer.parseInt(values[0].trim());
                        String firstName = values[1].trim();
                        String lastName = values[2].trim();
                        String email = values[3].trim();
                        String zipCode = values[4].trim();
                        String address = values[5].trim();

                        // Instantiates a contact
                        contacts.add(new Contact(contactId, firstName, lastName, email, zipCode, address));
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the CSV file: " + e.getMessage());
        }
        return contacts;
    }
}
