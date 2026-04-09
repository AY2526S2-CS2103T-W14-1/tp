package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.StringUtil.normalizeWhitespace;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OWNER_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SERVICE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.pet.Pet;
import seedu.address.model.service.Service;
import seedu.address.model.session.Session;

/**
 * Adds a session for a specific owner and pet with a start and end time.
 */
public class AddSessionCommand extends Command {

    public static final String COMMAND_WORD = "addsession";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a session. "
            + "Parameters: "
            + PREFIX_OWNER_INDEX + "OWNER_INDEX "
            + PREFIX_PET_INDEX + "PET_INDEX "
            + PREFIX_START_TIME + "START_TIME (yyyy-MM-dd HH:mm) "
            + PREFIX_END_TIME + "END_TIME (yyyy-MM-dd HH:mm) "
            + PREFIX_SERVICE_NAME + "SERVICE_NAME ["
            + PREFIX_SERVICE_NAME + "SERVICE_NAME]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_OWNER_INDEX + "1 "
            + PREFIX_PET_INDEX + "1 "
            + PREFIX_START_TIME + "2026-03-25 10:00 "
            + PREFIX_END_TIME + "2026-03-25 11:00 "
            + PREFIX_SERVICE_NAME + "Shampoo "
            + PREFIX_SERVICE_NAME + "Fur trim";

    public static final String MESSAGE_SUCCESS = "Added session for %s's pet %s from %s to %s.";
    public static final String MESSAGE_UNKNOWN_SERVICE = "Unknown service: %1$s.";
    public static final String MESSAGE_MISSING_SERVICE = "At least one service must be specified.";
    public static final String MESSAGE_OVERLAPPING_SESSION =
            "Session overlaps with an existing session for the selected pet.";


    private final Index ownerIndex;
    private final Index petIndex;
    private final String startTime;
    private final String endTime;
    private final List<String> serviceNames;

    /**
     * Creates an AddSessionCommand.
     *
     * @param ownerIndex   Index of the owner in the displayed list
     * @param petIndex     Index of the pet within that owner's pet list
     * @param startTime    Start time of the session
     * @param endTime      End time of the session
     * @param serviceNames Names of services to attach to this session
     */
    public AddSessionCommand(Index ownerIndex, Index petIndex,
                             String startTime, String endTime, List<String> serviceNames) {
        requireNonNull(ownerIndex);
        requireNonNull(petIndex);
        requireNonNull(startTime);
        requireNonNull(endTime);
        requireNonNull(serviceNames);
        if (serviceNames.isEmpty()) {
            throw new IllegalArgumentException(MESSAGE_MISSING_SERVICE);
        }
        this.ownerIndex = ownerIndex;
        this.petIndex = petIndex;
        this.startTime = normalizeWhitespace(startTime);
        this.endTime = normalizeWhitespace(endTime);
        this.serviceNames = serviceNames.stream()
                .map(AddSessionCommand::normalizeServiceName)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownList = model.getFilteredPersonList();
        if (ownerIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_OWNER_DISPLAYED_INDEX);
        }

        Person owner = lastShownList.get(ownerIndex.getZeroBased());

        if (petIndex.getZeroBased() >= owner.getPetCount()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX);
        }

        Pet pet = owner.getPetList().get(petIndex.getZeroBased());

        List<Service> selectedServices = resolveServices(model.getServiceList());
        double totalFee = calculateTotalFee(selectedServices);
        Session newSession;
        try {
            newSession = new Session(startTime, endTime, totalFee, selectedServices);
        } catch (IllegalArgumentException e) {
            throw new CommandException(e.getMessage(), e);
        }

        if (pet.hasOverlappingSession(newSession)) {
            throw new CommandException(MESSAGE_OVERLAPPING_SESSION);
        }

        pet.addSession(newSession);
        model.updateDisplayedSessions(model.getFilteredPersonList());

        String baseMessage = String.format(MESSAGE_SUCCESS, owner.getName(), pet.getName(), startTime, endTime);
        return new CommandResult(baseMessage + String.format(" Total fee: $%.2f.", totalFee));
    }

    private List<Service> resolveServices(List<Service> availableServices) throws CommandException {
        List<Service> resolvedServices = new ArrayList<>();
        for (String serviceName : serviceNames) {
            Service matchedService = findServiceByName(availableServices, serviceName)
                    .orElseThrow(() -> new CommandException(String.format(MESSAGE_UNKNOWN_SERVICE, serviceName)));
            resolvedServices.add(matchedService);
        }
        return resolvedServices;
    }

    /**
     * Calculates the total fee for all services in this session.
     *
     * @param selectedServices Services chosen for this session
     * @return Total fee for the selected services
     */
    private double calculateTotalFee(List<Service> selectedServices) {
        return selectedServices.stream()
                .mapToDouble(Service::getCost)
                .sum();
    }

    /**
     * Finds a service by name from the available services list.
     */
    private Optional<Service> findServiceByName(List<Service> availableServices, String serviceName) {
        for (Service service : availableServices) {
            if (service.hasSameName(serviceName)) {
                return Optional.of(service);
            }
        }
        return Optional.empty();
    }

    private static String normalizeServiceName(String serviceName) {
        return normalizeWhitespace(serviceName);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof AddSessionCommand)) {
            return false;
        }
        AddSessionCommand otherCommand = (AddSessionCommand) other;
        return ownerIndex.equals(otherCommand.ownerIndex)
                && petIndex.equals(otherCommand.petIndex)
                && startTime.equals(otherCommand.startTime)
                && endTime.equals(otherCommand.endTime)
                && serviceNames.equals(otherCommand.serviceNames);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("ownerIndex", ownerIndex)
                .add("petIndex", petIndex)
                .add("startTime", startTime)
                .add("endTime", endTime)
                .add("serviceNames", serviceNames)
                .toString();
    }
}
