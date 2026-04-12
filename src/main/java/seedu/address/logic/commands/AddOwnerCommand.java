package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OWNER_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Adds an Owner to PetLog.
 */
public class AddOwnerCommand extends Command {

    public static final String COMMAND_WORD = "addowner";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an owner to the address book. "
            + "Parameters: "
            + PREFIX_OWNER_NAME + "OWNER_NAME "
            + PREFIX_PHONE + "PHONE_NUMBER "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_OWNER_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "VIP";

    public static final String MESSAGE_SUCCESS = "Added owner: %1$s";
    public static final String MESSAGE_SUCCESS_WITH_PHONE_WARNING = "Added owner: %1$s\n"
            + "Warning: Phone contains non-numeric characters. Use editowner to amend if necessary.";
    public static final String MESSAGE_DUPLICATE_PERSON = "Owner already exists.";

    private final Person toAdd;

    /**
     * Creates an AddOwnerCommand to add the specified {@code Person}
     */
    public AddOwnerCommand(Person person) {
        requireNonNull(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(toAdd);
        model.updateDisplayedSessions(model.getFilteredPersonList());
        String messageTemplate = toAdd.getPhone().hasOnlyDigits()
                ? MESSAGE_SUCCESS
                : MESSAGE_SUCCESS_WITH_PHONE_WARNING;
        return new CommandResult(String.format(messageTemplate, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddOwnerCommand)) {
            return false;
        }

        AddOwnerCommand otherAddOwnerCommand = (AddOwnerCommand) other;
        return toAdd.equals(otherAddOwnerCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
