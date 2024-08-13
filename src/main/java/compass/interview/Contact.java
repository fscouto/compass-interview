package compass.interview;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * The {@code Contact} class represents a contact entity.
 * It contains basic information such as contact ID, first name, last name, email, zip code, and address.
 *
 * <p>This class uses Lombok annotations to automatically generate boilerplate code such as getters,
 * setters, constructors (with all the arguments), and `toString`, `equals`, and `hashCode` methods.</p>
 */
@Data
@AllArgsConstructor
public class Contact {
    private int contactId;
    private String firstName;
    private String lastName;
    private String email;
    private String zipCode;
    private String address;
}
