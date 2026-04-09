package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.session.Session.MESSAGE_DATETIME_CONSTRAINTS;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddSessionCommand;

public class AddSessionCommandParserTest {

    private static final String VALID_OWNER_INDEX = " oi/1";
    private static final String VALID_PET_INDEX = " pi/1";
    private static final String VALID_START = " st/2026-03-25 10:00";
    private static final String VALID_END = " et/2026-03-25 11:00";
    private static final String VALID_SERVICE = " sn/Shampoo";
    private static final String VALID_SERVICE_WITH_LONG_WHITESPACE = " sn/  Sham   poo  ";

    private final AddSessionCommandParser parser = new AddSessionCommandParser();

    @Test
    public void parse_allRequiredFieldsPresent_success() {
        assertParseSuccess(parser, VALID_OWNER_INDEX + VALID_PET_INDEX + VALID_START + VALID_END + VALID_SERVICE,
                new AddSessionCommand(Index.fromOneBased(1), Index.fromOneBased(1),
                        "2026-03-25 10:00", "2026-03-25 11:00", List.of("Shampoo")));
    }

    @Test
    public void parse_optionalServicesPresent_success() {
        assertParseSuccess(parser, VALID_OWNER_INDEX + VALID_PET_INDEX + VALID_START + VALID_END + VALID_SERVICE,
                new AddSessionCommand(Index.fromOneBased(1), Index.fromOneBased(1),
                        "2026-03-25 10:00", "2026-03-25 11:00", List.of("Shampoo")));
    }

    @Test
    public void parse_optionalServicesWithLongWhitespace_success() {
        assertParseSuccess(parser,
                VALID_OWNER_INDEX + VALID_PET_INDEX + VALID_START + VALID_END + VALID_SERVICE_WITH_LONG_WHITESPACE,
                new AddSessionCommand(Index.fromOneBased(1), Index.fromOneBased(1),
                        "2026-03-25 10:00", "2026-03-25 11:00", List.of("Sham poo")));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddSessionCommand.MESSAGE_USAGE);

        assertParseFailure(parser, VALID_PET_INDEX + VALID_START + VALID_END + VALID_SERVICE, expectedMessage);
        assertParseFailure(parser, VALID_OWNER_INDEX + VALID_START + VALID_END + VALID_SERVICE, expectedMessage);
        assertParseFailure(parser, VALID_OWNER_INDEX + VALID_PET_INDEX + VALID_END + VALID_SERVICE, expectedMessage);
        assertParseFailure(parser, VALID_OWNER_INDEX + VALID_PET_INDEX + VALID_START + VALID_SERVICE,
                expectedMessage);
        assertParseFailure(parser, VALID_OWNER_INDEX + VALID_PET_INDEX + VALID_START + VALID_END, expectedMessage);
    }

    @Test
    public void parse_invalidIndex_failure() {
        assertParseFailure(parser, " oi/0" + VALID_PET_INDEX + VALID_START + VALID_END + VALID_SERVICE,
                ParserUtil.MESSAGE_INVALID_INDEX);
        assertParseFailure(parser, VALID_OWNER_INDEX + " pi/0" + VALID_START + VALID_END + VALID_SERVICE,
                ParserUtil.MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_invalidDateTime_failure() {
        assertParseFailure(parser, VALID_OWNER_INDEX + VALID_PET_INDEX + " st/2026/03/25 10:00" + VALID_END
                + VALID_SERVICE,
                MESSAGE_DATETIME_CONSTRAINTS);
        assertParseFailure(parser, VALID_OWNER_INDEX + VALID_PET_INDEX + VALID_START + " et/2026-02-30 11:00"
                + VALID_SERVICE,
                MESSAGE_DATETIME_CONSTRAINTS);
    }

    @Test
    public void parse_duplicateRequiredPrefixes_failure() {
        String validInput = VALID_OWNER_INDEX + VALID_PET_INDEX + VALID_START + VALID_END + VALID_SERVICE;

        assertParseFailure(parser, " oi/2" + validInput,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_OWNER_INDEX));
        assertParseFailure(parser, " pi/2" + validInput,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_PET_INDEX));
        assertParseFailure(parser, " st/2026-03-25 09:00" + validInput,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_START_TIME));
        assertParseFailure(parser, " et/2026-03-25 12:00" + validInput,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_END_TIME));
    }

    @Test
    public void parse_nonEmptyPreamble_failure() {
        assertParseFailure(parser, " random" + VALID_OWNER_INDEX + VALID_PET_INDEX + VALID_START + VALID_END
                + VALID_SERVICE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddSessionCommand.MESSAGE_USAGE));
    }
}
