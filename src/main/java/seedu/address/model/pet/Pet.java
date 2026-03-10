package seedu.address.model.pet;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a Pet in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Pet {

    // Identity fields
    private final PetName name;
    private final Species species;
    private final OwnerIndex ownerIndex;

    // Data fields
    private final PetRemark remark;

    /**
     * Every field must be present and not null.
     */
    public Pet(PetName name, Species species, OwnerIndex ownerIndex, PetRemark remark) {
        requireAllNonNull(name, species, ownerIndex, remark);
        this.name = name;
        this.species = species;
        this.ownerIndex = ownerIndex;
        this.remark = remark;
    }

    public PetName getName() {
        return name;
    }

    public Species getSpecies() {
        return species;
    }

    public OwnerIndex getOwnerIndex() {
        return ownerIndex;
    }

    public PetRemark getRemark() {
        return remark;
    }

    /**
     * Returns true if both pets have the same name, species, and owner index.
     * This defines a weaker notion of equality between two pets.
     */
    public boolean isSamePet(Pet otherPet) {
        if (otherPet == this) {
            return true;
        }

        return otherPet != null
                && otherPet.getName().equals(getName())
                && otherPet.getSpecies().equals(getSpecies())
                && otherPet.getOwnerIndex().equals(getOwnerIndex());
    }

    /**
     * Returns true if both pets have the same identity and data fields.
     * This defines a stronger notion of equality between two pets.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Pet)) {
            return false;
        }

        Pet otherPet = (Pet) other;
        return name.equals(otherPet.name)
                && species.equals(otherPet.species)
                && ownerIndex.equals(otherPet.ownerIndex)
                && remark.equals(otherPet.remark);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, species, ownerIndex, remark);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("species", species)
                .add("ownerIndex", ownerIndex)
                .add("remark", remark)
                .toString();
    }

}
