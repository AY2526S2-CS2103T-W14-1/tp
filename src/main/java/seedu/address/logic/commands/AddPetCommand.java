package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OWNER_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIES;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.pet.Pet;

/**
 * Adds a pet to the address book.
 */
public class AddPetCommand extends Command {

    public static final String COMMAND_WORD = "addpet";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a pet to the address book. "
            + "Parameters: "
            + PREFIX_OWNER_INDEX + "OWNER_INDEX "
            + PREFIX_PET_NAME + "PET_NAME "
            + PREFIX_SPECIES + "SPECIES "
            + "[" + PREFIX_PET_REMARK + "REMARKS]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_OWNER_INDEX + "1 "
            + PREFIX_PET_NAME + "Snowball "
            + PREFIX_SPECIES + "Cat "
            + PREFIX_PET_REMARK + "Feed hourly";

    public static final String MESSAGE_SUCCESS = "New pet added: %1$s";
    public static final String MESSAGE_DUPLICATE_PET = "This pet already exists in the address book";

    private final Pet toAdd;

    /**
     * Creates an AddPetCommand to add the specified {@code Pet}
     */
    public AddPetCommand(Pet pet) {
        requireNonNull(pet);
        toAdd = pet;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // Parser/model wiring for pets is not implemented yet.
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddPetCommand)) {
            return false;
        }

        AddPetCommand otherAddPetCommand = (AddPetCommand) other;
        return toAdd.equals(otherAddPetCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
