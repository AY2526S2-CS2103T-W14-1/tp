package seedu.address.model.pet;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.commons.util.StringUtil;

/**
 * Represents a Pet's owner index in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidOwnerIndex(String)}
 */
public class OwnerIndex {

    public static final String MESSAGE_CONSTRAINTS =
            "Owner index should be a non-zero unsigned integer.";

    public final String value;

    /**
     * Constructs an {@code OwnerIndex}.
     *
     * @param ownerIndex A valid owner index.
     */
    public OwnerIndex(String ownerIndex) {
        requireNonNull(ownerIndex);
        checkArgument(isValidOwnerIndex(ownerIndex), MESSAGE_CONSTRAINTS);
        value = ownerIndex;
    }

    /**
     * Returns true if a given string is a valid owner index.
     */
    public static boolean isValidOwnerIndex(String test) {
        return StringUtil.isNonZeroUnsignedInteger(test);
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
        if (!(other instanceof OwnerIndex)) {
            return false;
        }

        OwnerIndex otherIndex = (OwnerIndex) other;
        return value.equals(otherIndex.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
