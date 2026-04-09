package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.person.Person;
import seedu.address.model.pet.Pet;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class OwnerCard extends UiPart<Region> {

    private static final String FXML = "OwnerCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private VBox petsContainer;

    /**
     * Creates a {@code OwnerCard} with the given {@code Person} and index to display.
     */
    public OwnerCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        for (int i = 0; i < person.getPetList().size(); i++) {
            Pet pet = person.getPetList().get(i);
            petsContainer.getChildren().add(createPetLabel(pet, i + 1));
        }
    }

    private Label createPetLabel(Pet pet, int ownerPetIndex) {
        String petSummary = ownerPetIndex + ". " + pet.getName().value + " • " + pet.getSpecies().value;
        String petRemarkSuffix = pet.getRemark().value.isEmpty() ? "" : "\n" + pet.getRemark().value;
        Label label = new Label(petSummary + petRemarkSuffix);
        label.setWrapText(true);
        label.getStyleClass().add("pet-summary-label");
        return label;
    }
}
