package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.pet.Pet;
import seedu.address.model.service.Service;
import seedu.address.model.session.Session;
import seedu.address.testutil.TypicalAddressBooks;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code AddSessionCommand}.
 */
public class AddSessionCommandTest {

    private static final String VALID_START = "2026-03-25 10:00";
    private static final String VALID_END = "2026-03-25 11:00";
    private static final String VALID_START_2 = "2026-03-26 14:00";
    private static final String VALID_END_2 = "2026-03-26 15:00";
    private static final String OVERLAPPING_START = "2026-03-25 10:30";
    private static final String OVERLAPPING_END = "2026-03-25 11:30";
    private static final String ADJACENT_START = "2026-03-25 11:00";
    private static final String ADJACENT_END = "2026-03-25 12:00";
    private static final String SHAMPOO = "Shampoo";
    private static final String NAIL_TRIM = "Nail trim";

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalAddressBooks.getTypicalPetLog(), new UserPrefs());
    }

    @Test
    public void execute_validIndices_success() {
        Person owner = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Pet pet = owner.getPetList().get(INDEX_FIRST_PERSON.getZeroBased());

        AddSessionCommand command = new AddSessionCommand(
                INDEX_FIRST_PERSON, INDEX_FIRST_PERSON, VALID_START, VALID_END, List.of(SHAMPOO));

        String expectedMessage = String.format(AddSessionCommand.MESSAGE_SUCCESS,
                owner.getName(), pet.getName(), VALID_START, VALID_END) + " Total fee: $30.00.";

        // Model equality is unaffected by session addition (sessions excluded from equals)
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sessionAddedToPet() throws Exception {
        AddSessionCommand command = new AddSessionCommand(
                INDEX_FIRST_PERSON, INDEX_FIRST_PERSON, VALID_START, VALID_END, List.of(SHAMPOO));
        command.execute(model);

        Pet pet = model.getFilteredPersonList()
                .get(INDEX_FIRST_PERSON.getZeroBased())
                .getPetList()
                .get(INDEX_FIRST_PERSON.getZeroBased());

        assertEquals(1, pet.getSessions().size());
        assertEquals(VALID_START, pet.getSessions().get(0).getStartTime());
        assertEquals(VALID_END, pet.getSessions().get(0).getEndTime());
    }

    @Test
    public void execute_multipleSessionsAddedToPet() throws Exception {
        AddSessionCommand first = new AddSessionCommand(
                INDEX_FIRST_PERSON, INDEX_FIRST_PERSON, VALID_START, VALID_END, List.of(SHAMPOO));
        AddSessionCommand second = new AddSessionCommand(
                INDEX_FIRST_PERSON, INDEX_FIRST_PERSON, VALID_START_2, VALID_END_2, List.of(NAIL_TRIM));

        first.execute(model);
        second.execute(model);

        Pet pet = model.getFilteredPersonList()
                .get(INDEX_FIRST_PERSON.getZeroBased())
                .getPetList()
                .get(INDEX_FIRST_PERSON.getZeroBased());

        assertEquals(2, pet.getSessions().size());
        assertEquals(new Session(VALID_START, VALID_END, 30.0, List.of(new Service(SHAMPOO, 30))),
                pet.getSessions().get(0));
        assertEquals(new Session(VALID_START_2, VALID_END_2, 10.0, List.of(new Service(NAIL_TRIM, 10))),
                pet.getSessions().get(1));
    }

    @Test
    public void execute_sessionListUpdatedInModel() throws Exception {
        AddSessionCommand command = new AddSessionCommand(
                INDEX_FIRST_PERSON, INDEX_FIRST_PERSON, VALID_START, VALID_END, List.of(SHAMPOO));
        command.execute(model);

        assertEquals(1, model.getSessionList().size());
        assertEquals(new Session(VALID_START, VALID_END, 30.0, List.of(new Service(SHAMPOO, 30))),
                model.getSessionList().get(0).session());
    }

    @Test
    public void execute_invalidOwnerIndex_throwsCommandException() {
        Index outOfBound = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        AddSessionCommand command = new AddSessionCommand(
                outOfBound, INDEX_FIRST_PERSON, VALID_START, VALID_END, List.of(SHAMPOO));

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_OWNER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPetIndex_throwsCommandException() {
        Index outOfBound = Index.fromOneBased(2);
        AddSessionCommand command = new AddSessionCommand(
                INDEX_SECOND_PERSON, outOfBound, VALID_START, VALID_END, List.of(SHAMPOO));

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX);
    }

    @Test
    public void execute_endBeforeStart_throwsCommandException() {
        AddSessionCommand command = new AddSessionCommand(
                INDEX_FIRST_PERSON, INDEX_FIRST_PERSON, VALID_END, VALID_START, List.of(SHAMPOO));

        assertCommandFailure(command, model, Session.MESSAGE_INVALID_TIME_RANGE);
    }

    @Test
    public void execute_overlappingSession_throwsCommandException() throws Exception {
        AddSessionCommand first = new AddSessionCommand(
                INDEX_FIRST_PERSON, INDEX_FIRST_PERSON, VALID_START, VALID_END, List.of(SHAMPOO));
        first.execute(model);

        AddSessionCommand overlapping = new AddSessionCommand(
                INDEX_FIRST_PERSON, INDEX_FIRST_PERSON, OVERLAPPING_START, OVERLAPPING_END, List.of(SHAMPOO));

        assertCommandFailure(overlapping, model, AddSessionCommand.MESSAGE_OVERLAPPING_SESSION);
    }

    @Test
    public void execute_adjacentSession_success() throws Exception {
        AddSessionCommand first = new AddSessionCommand(
                INDEX_FIRST_PERSON, INDEX_FIRST_PERSON, VALID_START, VALID_END, List.of(SHAMPOO));
        AddSessionCommand adjacent = new AddSessionCommand(
                INDEX_FIRST_PERSON, INDEX_FIRST_PERSON, ADJACENT_START, ADJACENT_END, List.of(NAIL_TRIM));

        first.execute(model);
        adjacent.execute(model);

        Pet pet = model.getFilteredPersonList()
                .get(INDEX_FIRST_PERSON.getZeroBased())
                .getPetList()
                .get(INDEX_FIRST_PERSON.getZeroBased());

        assertEquals(2, pet.getSessions().size());
        assertEquals(new Session(ADJACENT_START, ADJACENT_END, 10.0, List.of(new Service(NAIL_TRIM, 10))),
                pet.getSessions().get(1));
    }

    @Test
    public void execute_withServices_servicesStoredAndFeeCalculated() throws Exception {
        Model modelWithServices = new ModelManager(TypicalAddressBooks.getTypicalPetLog(), new UserPrefs());
        AddSessionCommand command = new AddSessionCommand(
                INDEX_FIRST_PERSON, INDEX_FIRST_PERSON, VALID_START, VALID_END, List.of(SHAMPOO, NAIL_TRIM));
        command.execute(modelWithServices);

        Pet pet = modelWithServices.getFilteredPersonList()
                .get(INDEX_FIRST_PERSON.getZeroBased())
                .getPetList()
                .get(INDEX_FIRST_PERSON.getZeroBased());
        Session addedSession = pet.getSessions().get(0);

        Service shampooService = modelWithServices.getServiceList().stream()
                .filter(service -> service.getName().equals(SHAMPOO))
                .findFirst()
                .get();
        Service nailTrimService = modelWithServices.getServiceList().stream()
                .filter(service -> service.getName().equals(NAIL_TRIM))
                .findFirst()
                .get();

        assertEquals(List.of(shampooService, nailTrimService), addedSession.getServices());
        assertEquals(40.0, addedSession.getFee(), 1e-9);
    }

    @Test
    public void execute_withServicesCaseAndWhitespaceInsensitiveServiceMatch_success() throws Exception {
        Model modelWithServices = new ModelManager(TypicalAddressBooks.getTypicalPetLog(), new UserPrefs());
        AddSessionCommand command = new AddSessionCommand(
                INDEX_FIRST_PERSON, INDEX_FIRST_PERSON, VALID_START, VALID_END,
                List.of("  shampoo  ", "  Nail   trim "));
        command.execute(modelWithServices);

        Pet pet = modelWithServices.getFilteredPersonList()
                .get(INDEX_FIRST_PERSON.getZeroBased())
                .getPetList()
                .get(INDEX_FIRST_PERSON.getZeroBased());
        Session addedSession = pet.getSessions().get(0);

        assertEquals(2, addedSession.getServices().size());
        assertEquals(40.0, addedSession.getFee(), 1e-9);
    }

    @Test
    public void execute_withUnknownService_throwsCommandException() {
        Model modelWithServices = new ModelManager(TypicalAddressBooks.getTypicalPetLog(), new UserPrefs());
        AddSessionCommand command = new AddSessionCommand(
                INDEX_FIRST_PERSON, INDEX_FIRST_PERSON, VALID_START, VALID_END, List.of("Unknown service"));

        assertCommandFailure(command, modelWithServices,
                String.format(AddSessionCommand.MESSAGE_UNKNOWN_SERVICE, "Unknown service"));
    }

    @Test
    public void constructor_withoutServices_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                AddSessionCommand.MESSAGE_MISSING_SERVICE,
                () -> new AddSessionCommand(INDEX_FIRST_PERSON, INDEX_FIRST_PERSON,
                        VALID_START, VALID_END, List.of()));
    }

    @Test
    public void equals() {
        AddSessionCommand commandA = new AddSessionCommand(
                INDEX_FIRST_PERSON, INDEX_FIRST_PERSON, VALID_START, VALID_END, List.of(SHAMPOO));
        AddSessionCommand commandB = new AddSessionCommand(
                INDEX_SECOND_PERSON, INDEX_FIRST_PERSON, VALID_START, VALID_END, List.of(SHAMPOO));

        // same object -> true
        assertTrue(commandA.equals(commandA));

        // same values -> true
        AddSessionCommand commandACopy = new AddSessionCommand(
                INDEX_FIRST_PERSON, INDEX_FIRST_PERSON, VALID_START, VALID_END, List.of(SHAMPOO));
        assertTrue(commandA.equals(commandACopy));

        // null -> false
        assertFalse(commandA.equals(null));

        // different type -> false
        assertFalse(commandA.equals("string"));

        // different owner index -> false
        assertFalse(commandA.equals(commandB));

        // different pet index -> false
        AddSessionCommand commandDiffPet = new AddSessionCommand(
                INDEX_FIRST_PERSON, INDEX_SECOND_PERSON, VALID_START, VALID_END, List.of(SHAMPOO));
        assertFalse(commandA.equals(commandDiffPet));

        // different start time -> false
        AddSessionCommand commandDiffStart = new AddSessionCommand(
                INDEX_FIRST_PERSON, INDEX_FIRST_PERSON, VALID_START_2, VALID_END, List.of(SHAMPOO));
        assertFalse(commandA.equals(commandDiffStart));

        // different end time -> false
        AddSessionCommand commandDiffEnd = new AddSessionCommand(
                INDEX_FIRST_PERSON, INDEX_FIRST_PERSON, VALID_START, VALID_END_2, List.of(SHAMPOO));
        assertFalse(commandA.equals(commandDiffEnd));
    }

    @Test
    public void toStringMethod() {
        AddSessionCommand command = new AddSessionCommand(
                INDEX_FIRST_PERSON, INDEX_FIRST_PERSON, VALID_START, VALID_END, List.of(SHAMPOO));
        String expected = AddSessionCommand.class.getCanonicalName()
                + "{ownerIndex=" + INDEX_FIRST_PERSON
                + ", petIndex=" + INDEX_FIRST_PERSON
                + ", startTime=" + VALID_START
                + ", endTime=" + VALID_END
                + ", serviceNames=[Shampoo]}";
        assertEquals(expected, command.toString());
    }
}
