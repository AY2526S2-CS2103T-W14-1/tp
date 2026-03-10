package seedu.address.model.pet;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Pet's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class PetName {

    public static final String MESSAGE_CONSTRAINTS =
            "Pet names should be 1 to 15 characters long and only contain letters, spaces, hyphens or apostrophes.";

    public static final String VALIDATION_REGEX = "[A-Za-z][A-Za-z '\\-]{0,14}";

    public final String value;

    /**
     * Constructs a {@code PetName}.
     *
     * @param name A valid pet name.
     */
    public PetName(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        value = name;
    }

    /**
     * Returns true if a given string is a valid pet name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PetName)) {
            return false;
        }

        PetName otherName = (PetName) other;
        return value.equals(otherName.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
