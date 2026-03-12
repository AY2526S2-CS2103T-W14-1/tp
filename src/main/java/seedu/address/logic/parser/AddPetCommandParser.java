package seedu.address.logic.parser;

import seedu.address.logic.commands.AddOwnerCommand;
import seedu.address.logic.commands.AddPetCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.*;
import seedu.address.model.pet.*;
import seedu.address.model.tag.Tag;

import java.util.Set;
import java.util.stream.Stream;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

/**
 * Parses input arguments and creates a new AddPetCommand object
 */
public class AddPetCommandParser implements Parser<AddPetCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddPetCommand
     * and returns an AddPetCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddPetCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_OWNER_INDEX, PREFIX_PET_NAME, PREFIX_SPECIES, PREFIX_PET_REMARK);

        if (!arePrefixesPresent(argMultimap, PREFIX_OWNER_INDEX, PREFIX_PET_NAME, PREFIX_SPECIES)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPetCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_OWNER_INDEX, PREFIX_PET_NAME, PREFIX_SPECIES, PREFIX_PET_REMARK);
        OwnerIndex ownerIndex = ParserUtil.parseOwnerIndex(argMultimap.getValue(PREFIX_OWNER_INDEX).get());
        PetName petName = ParserUtil.parsePetName(argMultimap.getValue(PREFIX_PET_NAME).get());
        Species species = ParserUtil.parseSpecies(argMultimap.getValue(PREFIX_SPECIES).get());
        PetRemark petRemark = new PetRemark("");
        if (arePrefixesPresent(argMultimap, PREFIX_PET_REMARK)) {
            petRemark = ParserUtil.parsePetRemark(argMultimap.getValue(PREFIX_PET_REMARK).get());
        }
        Pet newPet = new Pet(petName, species, ownerIndex, petRemark);
        return new AddPetCommand(newPet);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
